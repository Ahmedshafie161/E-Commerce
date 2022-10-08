package com.example.shafie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shafie.data.remote.dtos.ProductDto

@Database(entities = [ProductDto::class], version = 1, exportSchema = false)
abstract class CustomRoomDb : RoomDatabase() {

    abstract fun productDao(): DbDao

    companion object {
        @Volatile
        private var INSTANCE: CustomRoomDb? = null

        fun getDataBase(context: Context): CustomRoomDb {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CustomRoomDb::class.java,
                    "product_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}