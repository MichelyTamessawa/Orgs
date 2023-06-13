package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.orgs.model.Product

@Dao
interface ProductDao {
    @Query("Select * from Product")
    fun getAll(): List<Product>

    @Insert
    fun insert(vararg product: Product)
}