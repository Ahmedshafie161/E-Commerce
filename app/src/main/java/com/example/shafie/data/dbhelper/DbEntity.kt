package com.example.shafie.data.dbhelper

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class DbEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val image_url: String,
    val name: String,
    val price: String,
    val rating: Int
    )

