package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityProductListBinding
import com.example.orgs.ui.adapter.ProductListAdapter

class ProductListActivity : AppCompatActivity() {
    private val adapter = ProductListAdapter(context = this) { product ->
        val intent =
            Intent(this, ProductDetailsActivity::class.java).apply { putExtra("product", product) }
        startActivity(intent)
    }
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
        val db = AppDataBase.getInstance(this)
        val dao = db.productDao()
        adapter.update(dao.getAll())
    }

    private fun fabConfig() {
        val fab = binding.extendedFAB
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