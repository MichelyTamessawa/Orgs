package com.example.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProductDao
import com.example.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)
        saveButtonConfig()
    }

    private fun saveButtonConfig() {
        val saveButton = findViewById<Button>(R.id.activity_product_form_button)
        val dao = ProductDao()

        saveButton.setOnClickListener {
            val newProduct = getNewProduct()
            dao.insert(newProduct)
            finish()
        }
    }

    private fun getNewProduct(): Product {
        val nameField = findViewById<EditText>(R.id.activity_product_form_name)
        val descriptionField = findViewById<EditText>(R.id.activity_product_form_description)
        val valueField = findViewById<EditText>(R.id.activity_product_form_value)
        val productValue = if (valueField.text.isBlank())
            BigDecimal.ZERO
        else
            BigDecimal(valueField.text.toString())
        return Product(
            name = nameField.text.toString(),
            description = descriptionField.text.toString(),
            value = productValue
        )
    }
}