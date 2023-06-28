package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.orgs.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("Select * from Product")
    fun getAll(): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg product: Product)

    @Delete
    suspend fun delete(vararg product: Product)

    @Update
    suspend fun update(vararg product: Product)

    @Query("Select * from Product where uid = :id")
    fun getById(id: Long): Flow<Product?>

    @Query("Select * from Product order by name desc")
    fun getAllSortedByNameDesc(): Flow<List<Product>>

    @Query("Select * from Product order by name asc")
    fun getAllSortedByNameAsc(): Flow<List<Product>>

    @Query("Select * from Product order by description desc")
    fun getAllSortedByDescriptionDesc(): Flow<List<Product>>

    @Query("Select * from Product order by description asc")
    fun getAllSortedByDescriptionAsc(): Flow<List<Product>>

    @Query("Select * from Product order by value desc")
    fun getAllSortedByValueDesc(): Flow<List<Product>>

    @Query("Select * from Product order by value asc")
    fun getAllSortedByValueAsc(): Flow<List<Product>>
}
