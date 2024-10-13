package com.seongjae.secureorder.data.source.api

import com.seongjae.secureorder.data.model.MenuDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MenuApi {

    @GET("filter.php")
    suspend fun getMenus(
        @Query("c") category: String
    ): List<MenuDataModel>

    fun getMenuCategories(): List<String>
}
