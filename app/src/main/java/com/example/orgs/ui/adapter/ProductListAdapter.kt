package com.example.orgs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.databinding.ProductItemBinding
import com.example.orgs.extensions.brCurrencyFormatter
import com.example.orgs.extensions.loadingImage
import com.example.orgs.model.Product

class ProductListAdapter(
    private val context: Context,
    productList: List<Product> = emptyList(),
    var onItemClicked: (product: Product) -> Unit = {},
    var onEditItem: (product: Product) -> Unit = {},
    var onDeleteItem: (product: Product) -> Unit = {},
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private val productList = productList.toMutableList()

    inner class ViewHolder(
        private val binding: ProductItemBinding,
        onItemClicked: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {
        private lateinit var product: Product

        init {
            itemView.setOnClickListener {
                if (::product.isInitialized)
                    onItemClicked(adapterPosition)
            }
            itemView.setOnLongClickListener {
                PopupMenu(context, itemView).apply {
                    menuInflater.inflate(R.menu.menu_product_details, menu)
                    setOnMenuItemClickListener(this@ViewHolder)
                }.show()
                true
            }
        }

        fun bind(product: Product) {
            this.product = product
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

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            item?.let {
                when (item.itemId) {
                    R.id.menu_product_details_edit -> onEditItem(product)
                    R.id.menu_product_details_delete -> onDeleteItem(product)
                }
            }
            return true
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
