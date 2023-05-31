package com.example.orgs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ProductItemBinding
import com.example.orgs.model.Product

class ProductListAdapter(
    val context: Context,
    productList: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private val productList = productList.toMutableList()

    class ViewHolder(binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val nameField = binding.productItemName
        private val descriptionField = binding.productItemDescription
        private val valueField = binding.productItemValue

        fun bind(product: Product) {
            nameField.text = product.name
            descriptionField.text = product.description
            valueField.text = product.value.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    fun update(newList: List<Product>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }
}
