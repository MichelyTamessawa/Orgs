package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.R
import com.example.orgs.database.AppDataBase
import com.example.orgs.database.dao.ProductDao
import com.example.orgs.databinding.ActivityProductListBinding
import com.example.orgs.model.Product
import com.example.orgs.ui.adapter.ProductListAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductListActivity : AppCompatActivity() {
    private val adapter = ProductListAdapter(context = this)

    private val binding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }

    private val productDao: ProductDao by lazy {
        AppDataBase.getInstance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        recyclerViewConfig()
        fabConfig()
        lifecycleScope.launch {
            productDao.getAll().collect {
                adapter.update(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lifecycleScope.launch {
            val productListSorted: Flow<List<Product>>? =
                when (item.itemId) {
                    R.id.name_desc -> productDao.getAllSortedByNameDesc()
                    R.id.name_asc -> productDao.getAllSortedByNameAsc()
                    R.id.description_desc -> productDao.getAllSortedByDescriptionDesc()
                    R.id.description_asc -> productDao.getAllSortedByDescriptionAsc()
                    R.id.value_desc -> productDao.getAllSortedByValueDesc()
                    R.id.value_asc -> productDao.getAllSortedByValueAsc()
                    R.id.no_sort -> productDao.getAll()
                    else -> null
                }
            productListSorted?.collect {
                adapter.update(it)
            }
        }
        return super.onOptionsItemSelected(item)
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
        adapter.onItemClicked = {
            val intent =
                Intent(this, ProductDetailsActivity::class.java)
                    .apply { putExtra(PRODUCT_ID_KEY, it.uid) }
            startActivity(intent)
        }
        adapter.onEditItem = {
            val intent = Intent(this, ProductFormActivity::class.java)
                .apply { putExtra(PRODUCT_ID_KEY, it.uid) }
            startActivity(intent)
        }
        adapter.onDeleteItem = {
            lifecycleScope.launch {
                it.let { productDao.delete(it) }
                productDao.getAll().collect {
                    adapter.update(it)
                }
            }
        }
    }
}