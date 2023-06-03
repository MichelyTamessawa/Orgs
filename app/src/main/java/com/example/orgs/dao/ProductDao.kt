package com.example.orgs.dao

import com.example.orgs.model.Product
import java.math.BigDecimal

class ProductDao {

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
    }
}