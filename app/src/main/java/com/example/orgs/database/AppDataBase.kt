package com.example.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.orgs.database.converters.Converters
import com.example.orgs.database.dao.ProductDao
import com.example.orgs.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var db: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {
            return db ?: Room.databaseBuilder(context, AppDataBase::class.java, "orgs.db")
                .build()
                .also { db = it }
        }
    }
}