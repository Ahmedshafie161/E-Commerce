package com.example.shafie.data.dbhelper

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DbDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductToDatabase(product:DbEntity)

    @Query("select * from product_table")
    fun getProductFromDatabase():List<DbEntity>

}