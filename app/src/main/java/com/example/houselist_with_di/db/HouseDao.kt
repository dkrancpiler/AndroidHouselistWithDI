package com.example.houselist_with_di.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HouseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(DataX: HouseDbEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertpage(Pagination: Int): Long

    @Query("Select * FROM House")
    suspend fun get(): List<HouseDbEntity>

    @Query("Select * From Pages")
    suspend fun getPages(): Int
    }