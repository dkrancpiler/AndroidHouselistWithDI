package com.example.houselist_with_di.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HouseDbEntity::class], version = 1)
abstract class HousesDatabase: RoomDatabase() {
    abstract fun houseDao(): HouseDao
    companion object {
        val DATABASE_NAME: String = "house_db"
    }
}