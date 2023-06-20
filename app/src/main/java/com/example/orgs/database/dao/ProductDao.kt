package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.orgs.model.Product

@Dao
interface ProductDao {
    @Query("Select * from Product")
    fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg product: Product)

    @Delete
    fun delete(vararg product: Product)

    @Update
    fun update(vararg product: Product)

    @Query("Select * from Product where uid = :id")
    fun getById(id: Long): Product?

    @Query("Select * from Product order by name desc")
    fun getAllSortedByNameDesc(): List<Product>

    @Query("Select * from Product order by name asc")
    fun getAllSortedByNameAsc(): List<Product>

    @Query("Select * from Product order by description desc")
    fun getAllSortedByDescriptionDesc(): List<Product>

    @Query("Select * from Product order by description asc")
    fun getAllSortedByDescriptionAsc(): List<Product>

    @Query("Select * from Product order by value desc")
    fun getAllSortedByValueDesc(): List<Product>

    @Query("Select * from Product order by value asc")
    fun getAllSortedByValueAsc(): List<Product>
}
