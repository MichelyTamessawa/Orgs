package com.example.orgs.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.databinding.ActivityProductDetailsBinding
import com.example.orgs.extensions.brCurrencyFormatter
import com.example.orgs.extensions.loadingImage
import com.example.orgs.model.Product

private const val PRODUCT = "product"

class ProductDetailsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityProductDetailsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(PRODUCT)) {
            finish()
        }
        val product = getExtraProduct()
        bindViews(product)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_product_details_edit -> {
                Log.i(localClassName, "edit")
            }
            R.id.menu_product_details_delete -> {
                Log.i(localClassName, "delete")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bindViews(product: Product?) {
        with(binding) { activityProductDetailsImage.loadingImage(product?.imageUrl)
            activityProductDetailsName.text = product?.name
            activityProductDetailsDescription.text = product?.description
            activityProductDetailsValue.text = product?.value?.brCurrencyFormatter()
        }
    }

    private fun getExtraProduct(): Product? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(PRODUCT, Product::class.java)
        else
            intent.getParcelableExtra(PRODUCT)
    }
}
