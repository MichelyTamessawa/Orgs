package com.example.orgs.ui.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityProductFormBinding
import com.example.orgs.extensions.loadingImage
import com.example.orgs.model.Product
import com.example.orgs.ui.dialog.ImageFormDialog
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private var idProduct: Long = 0L

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private val productDao by lazy {
        AppDataBase.getInstance(this).productDao()
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Register Product"
        saveButtonConfig()
        imageViewConfig()
        tryGetProduct()
    }

    override fun onResume() {
        super.onResume()
        productDao.getById(idProduct)?.let {
            title = "Update Product"
            bindViews(it)
        }
    }

    private fun tryGetProduct() {
        idProduct = intent.getLongExtra(PRODUCT_ID_KEY, 0L)
    }

    private fun bindViews(product: Product) {
        url = product.imageUrl
        binding.activityProductFormName.setText(product.name)
        binding.activityProductFormDescription.setText(product.description)
        binding.activityProductFormValue.setText(product.value.toPlainString())
        binding.activityProductFormImage.loadingImage(product.imageUrl)
    }

    private fun imageViewConfig() {
        binding.activityProductFormImage.setOnClickListener {
            ImageFormDialog(this).show(url) { imageUrl ->
                url = imageUrl
                binding.activityProductFormImage.loadingImage(url)
            }
        }
    }

    private fun saveButtonConfig() {
        val saveButton = binding.activityProductFormButton
        saveButton.setOnClickListener {
            val product = getNewProduct()
            productDao.insert(product)
            finish()
        }
    }

    private fun getNewProduct(): Product {
        val nameField = binding.activityProductFormName
        val descriptionField = binding.activityProductFormDescription
        val valueField = binding.activityProductFormValue

        return Product(
            uid = idProduct,
            name = nameField.text.toString(),
            description = descriptionField.text.toString(),
            value = getNewProductValue(valueField),
            imageUrl = this.url
        )
    }

    private fun getNewProductValue(valueField: EditText): BigDecimal {
        return if (valueField.text.isBlank())
            BigDecimal.ZERO
        else
            BigDecimal(valueField.text.toString())
    }
}