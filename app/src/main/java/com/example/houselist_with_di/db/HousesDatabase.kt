package com.example.houselist_with_di.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HouseDbEntity::class, PageEntity::class], version = 2)
abstract class HousesDatabase: RoomDatabase() {
    abstract fun houseDao(): HouseDao
    companion object {
        val DATABASE_NAME: String = "house_db"
    }
}