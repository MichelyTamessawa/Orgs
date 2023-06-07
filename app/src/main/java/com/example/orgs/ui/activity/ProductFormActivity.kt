package com.example.orgs.ui.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProductDao
import com.example.orgs.databinding.ActivityProductFormBinding
import com.example.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        saveButtonConfig()
        binding.activityProductFormImage.setOnClickListener {
            AlertDialog.Builder(this)
                .setView(R.layout.dialog_form_image)
                .setPositiveButton("Confirmar") { _, _ ->
                }
                .setNegativeButton("Cancelar") { _, _ ->
                }
                .show()
        }
    }

    private fun saveButtonConfig() {
        val saveButton = binding.activityProductFormButton
        val dao = ProductDao()

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
            value = getNewProductValue(valueField)
        )
    }

    private fun getNewProductValue(valueField: EditText): BigDecimal {
        return if (valueField.text.isBlank())
            BigDecimal.ZERO
        else
            BigDecimal(valueField.text.toString())
    }
}