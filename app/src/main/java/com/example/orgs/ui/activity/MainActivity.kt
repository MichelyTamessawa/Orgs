package com.example.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Product
import com.example.orgs.ui.adapter.ProductListAdapter
import java.math.BigDecimal

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ProductListAdapter(context = this, productList = listOf(
            Product(name = "Orange", description = "Fruit", BigDecimal.valueOf(1.87)),
            Product(name = "Apple", description = "Fruit", BigDecimal.valueOf(10.87)),
            Product(name = "Watermelon", description = "Fruit", BigDecimal.valueOf(9.87)),
            Product(name = "Grape", description = "Fruit", BigDecimal.valueOf(15.87)),
        ))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}