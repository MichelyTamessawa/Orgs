package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityProductDetailsBinding
import com.example.orgs.extensions.brCurrencyFormatter
import com.example.orgs.extensions.loadingImage
import com.example.orgs.model.Product

class ProductDetailsActivity : AppCompatActivity() {
    private var idProduct: Long? = null
    private var product: Product? = null

    private val binding by lazy { ActivityProductDetailsBinding.inflate(layoutInflater) }
    private val productDao by lazy {
        AppDataBase.getInstance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryGetProduct()
    }

    override fun onResume() {
        super.onResume()
        getProduct()
    }

    private fun getProduct() {
        idProduct?.let {
            product = productDao.getById(it)
        }
        product?.let {
            bindViews(product)
        } ?: finish()
    }

    private fun tryGetProduct() {
        idProduct = intent.getLongExtra(PRODUCT_ID_KEY, 0L)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_product_details_edit -> {
                val intent = Intent(this, ProductFormActivity::class.java)
                    .apply { putExtra(PRODUCT_ID_KEY, idProduct) }
                startActivity(intent)
            }

            R.id.menu_product_details_delete -> {
                product?.let { productDao.delete(it) }
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bindViews(product: Product?) {
        with(binding) {
            activityProductDetailsImage.loadingImage(product?.imageUrl)
            activityProductDetailsName.text = product?.name
            activityProductDetailsDescription.text = product?.description
            activityProductDetailsValue.text = product?.value?.brCurrencyFormatter()
        }
    }
}
