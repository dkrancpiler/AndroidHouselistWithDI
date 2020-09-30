package com.example.houselist_with_di.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "House")
data class HouseDbEntity (
    @PrimaryKey
    @ColumnInfo(name="id")
    var id: Int,
    @ColumnInfo(name="title")
    var title: String,
    @ColumnInfo(name="title_short")
    var title_short: String,
//    @ColumnInfo(name="image")
//    var image: String,
    @ColumnInfo(name="description")
    var description: String,
    @ColumnInfo(name="description_short")
    var description_short: String,
    @ColumnInfo(name = "price")
    var price: Int,
    @ColumnInfo(name = "address")
    var address: String
) {
}