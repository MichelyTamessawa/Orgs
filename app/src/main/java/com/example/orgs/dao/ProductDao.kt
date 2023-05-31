package com.example.orgs.dao

import com.example.orgs.model.Product

class ProductDao {

    fun getAll() : List<Product> {
        return productList
    }

    fun insert(product: Product) {
        productList.add(product)
    }

    companion object {
        private val productList = mutableListOf<Product>()
    }
}