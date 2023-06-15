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
    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Register Product"
        saveButtonConfig()
        binding.activityProductFormImage.setOnClickListener {
            ImageFormDialog(this).show(url) { imageUrl ->
                url = imageUrl
                binding.activityProductFormImage.loadingImage(url)
            }
        }
    }

    private fun saveButtonConfig() {
        val saveButton = binding.activityProductFormButton
        val db = AppDataBase.getInstance(this)
        val dao = db.productDao()

        saveButton.setOnClickListener {
            val newProduct = getNewProduct()
            dao.insert(newProduct)
            finish()
        }
    }

    private fun getNewProduct(): Product {
        val nameField = binding.activityProductFormName
        val descriptionField = binding.activityProductFormDescription
        val valueField = binding.activityProductFormValue

        return Product(
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