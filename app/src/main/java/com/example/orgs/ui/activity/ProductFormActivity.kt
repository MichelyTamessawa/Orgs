package com.example.orgs.ui.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.orgs.dao.ProductDao
import com.example.orgs.databinding.ActivityProductFormBinding
import com.example.orgs.databinding.DialogImageUploadBinding
import com.example.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private val bindingFormImage by lazy {
        DialogImageUploadBinding.inflate(layoutInflater)
    }

    private var url : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        saveButtonConfig()
        binding.activityProductFormImage.setOnClickListener {
            val dialogUploadButton = bindingFormImage.dialogImageUploadButton
            val dialogUrlTextView = bindingFormImage.dialogImageUploadUrl
            val dialogImageView = bindingFormImage.dialogImageUploadImageview
            dialogUploadButton.setOnClickListener {
                if (dialogUrlTextView.text?.isNotBlank()!!)
                    dialogImageView.load(dialogUrlTextView.text.toString())
            }
            AlertDialog.Builder(this)
                .setView(bindingFormImage.root)
                .setPositiveButton("Confirm") { _, _ ->
                    if (dialogUrlTextView.text?.isNotBlank()!!) {
                        this.url = dialogUrlTextView.text.toString()
                        binding.activityProductFormImage.load(this.url)
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
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