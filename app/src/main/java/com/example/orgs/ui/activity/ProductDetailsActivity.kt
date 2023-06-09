package com.example.orgs.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        supportActionBar?.hide()
        if (!intent.hasExtra(PRODUCT)) {
            finish()
        }
        val product = getExtraProduct()
        bindViews(product)
    }

    private fun bindViews(product: Product?) {
        binding.activityProductDetailsImage.loadingImage(product?.imageUrl)
        binding.activityProductDetailsName.text = product?.name
        binding.activityProductDetailsDescription.text = product?.description
        binding.activityProductDetailsValue.text = product?.value?.brCurrencyFormatter()
    }

    private fun getExtraProduct(): Product? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(PRODUCT, Product::class.java)
        else
            intent.getParcelableExtra(PRODUCT)
    }
}
