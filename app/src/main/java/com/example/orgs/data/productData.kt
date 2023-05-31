package com.example.orgs.data

import com.example.orgs.model.Product
import java.math.BigDecimal

fun productData(): List<Product> {
    return listOf(
        Product(name = "Orange", description = "Fruit", BigDecimal.valueOf(1.87)),
        Product(name = "Apple", description = "Fruit", BigDecimal.valueOf(10.87)),
        Product(name = "Watermelon", description = "Fruit", BigDecimal.valueOf(9.87)),
        Product(name = "Grape", description = "Fruit", BigDecimal.valueOf(15.87)),
    )
}