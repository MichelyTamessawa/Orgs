package com.example.orgs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ProductItemBinding
import com.example.orgs.extensions.brCurrencyFormatter
import com.example.orgs.extensions.loadingImage
import com.example.orgs.model.Product
import java.text.NumberFormat
import java.util.Locale

class ProductListAdapter(
    val context: Context,
    productList: List<Product> = emptyList(),
    var onItemClicked: (Product) -> Unit
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private val productList = productList.toMutableList()

    class ViewHolder(
        private val binding: ProductItemBinding,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener { onItemClicked(adapterPosition) }
        }

        fun bind(product: Product) {
            val nameField = binding.productItemName
            val descriptionField = binding.productItemDescription
            val valueField = binding.productItemValue
            val imageView = binding.imageView
            nameField.text = product.name
            descriptionField.text = product.description
            valueField.text = product.value.brCurrencyFormatter()
            imageView.visibility =
                if (product.imageUrl != null)
                    View.VISIBLE
                else
                    View.GONE
            imageView.loadingImage(product.imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding) { onItemClicked(productList[it]) }
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
