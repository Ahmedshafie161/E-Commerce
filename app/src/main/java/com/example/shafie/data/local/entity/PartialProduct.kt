package com.example.shafie.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class PartialProduct(
    @ColumnInfo(name = "added_to_cart")
    val addedToCart: Boolean = false,
    @ColumnInfo(name = "id")
    val id: Int
)