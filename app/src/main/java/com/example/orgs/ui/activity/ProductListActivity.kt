package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.dao.ProductDao
import com.example.orgs.databinding.ActivityProductListBinding
import com.example.orgs.ui.adapter.ProductListAdapter

class ProductListActivity : AppCompatActivity() {
    private val productDao = ProductDao()
    private val adapter = ProductListAdapter(context = this, productDao.getAll())
    private val binding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        recyclerViewConfig()
        fabConfig()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(productDao.getAll())
    }

    private fun fabConfig() {
        val fab = binding.floatingActionButton
        fab.setOnClickListener {
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun recyclerViewConfig() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}