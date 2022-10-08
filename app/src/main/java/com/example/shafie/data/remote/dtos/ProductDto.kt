package com.example.shafie.data.remote.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product_entity")
data class ProductDto(
    @SerializedName("image_url")
    val imageUrl: String?,
    val name: String?,
    val price: String?,
    val rating: Int?,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)