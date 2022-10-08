package com.example.shafie.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shafie.data.remote.dtos.ProductDto

@Dao
interface DbDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductToDatabase(product: ProductDto)

    @Query("select * from product_entity")
    fun getProductFromDatabase(): List<ProductDto>

}