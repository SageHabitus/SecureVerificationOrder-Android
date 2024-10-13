package com.seongjae.secureorder.data.repository

import com.seongjae.secureorder.data.source.api.MenuApi
import com.seongjae.secureorder.data.source.dao.MenuDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * interface 생략 구현체만 작성
 */
class MenuRepository @Inject constructor(
    private val menuDao: MenuDao,
    private val menuApi: MenuApi
) {

    suspend fun getMenuCategories() = flow {
        val localCategories = menuDao.getCategories()
        if (localCategories.isNotEmpty()) {
            emit(localCategories)
        } else {
            val remoteCategories = menuApi.getMenuCategories()
            emit(remoteCategories)
        }
    }

    suspend fun getMenusByCategory(category: String) = flow {
        val localMenus = menuDao.getMenusByCategory(category = category)
        if (localMenus.isNotEmpty()) {
            emit(localMenus)
        } else {
            val menus = menuApi.getMenus(category)
            menuDao.insertMenus(menus)
            emit(menus)
        }
    }
}