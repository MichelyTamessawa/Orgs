package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.orgs.model.Product
import java.math.BigDecimal

@Dao
interface ProductDao {
    @Query("Select * from Product")
    fun getAll(): List<Product>

    @Insert
    fun insert(vararg product: Product)
}
/*

fun getAll(): List<Product> {
    return productList
}

fun insert(product: Product) {
    productList.add(product)
}

companion object {
    private val productList = mutableListOf(
        Product(
            name = "Salada de Frutas",
            description = "Muitas frutas",
            value = BigDecimal("12.34")
        )
    )
}*/
