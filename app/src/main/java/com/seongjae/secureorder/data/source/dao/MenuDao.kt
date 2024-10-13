package com.seongjae.secureorder.data.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seongjae.secureorder.data.model.MenuDataModel

@Dao
interface MenuDao {


    @Query("SELECT DISTINCT category FROM menu")
    fun getCategories(): List<String>

    @Query("SELECT * FROM menu WHERE category = :category")
    fun getMenusByCategory(category: String): List<MenuDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenus(menus: List<MenuDataModel>)
}
