package com.example.shafie.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shafie.data.local.entity.PartialProduct
import com.example.shafie.data.remote.dtos.ProductDto

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductToDatabase(product: ProductDto)

    @Query("select * from product_entity")
    fun getAll(): List<ProductDto>

    @Update(entity = ProductDto::class)
    suspend fun updateAll(partialProduct: List<PartialProduct>)

    @Query("SELECT * FROM product_entity WHERE added_to_cart = 1")
    suspend fun getAllProductsAddedToCart(): List<ProductDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(product: List<ProductDto>)

    @Update(entity = ProductDto::class)
    @Throws(Exception::class)
    suspend fun update(partialProduct: PartialProduct)

}