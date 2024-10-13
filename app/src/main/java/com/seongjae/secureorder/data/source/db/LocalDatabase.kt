package com.seongjae.secureorder.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seongjae.secureorder.data.model.MenuDataModel
import com.seongjae.secureorder.data.source.dao.MenuDao

@Database(
    entities = [MenuDataModel::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun menuDao(): MenuDao
}
