package com.example.sqlshoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlshoppinglist.R
import com.example.sqlshoppinglist.models.Product

class ProductAdapter(
    private var productList: List<Product>,
    private val onEdit: (Product) -> Unit,
    private val onDelete: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.productName)
        val countTextView: TextView = itemView.findViewById(R.id.productCount)
        val editButton: ImageButton = itemView.findViewById(R.id.editButton)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.nameTextView.text = product.name
        holder.countTextView.text = "Count: ${product.count}"

        holder.editButton.setOnClickListener { onEdit(product) }
        holder.deleteButton.setOnClickListener { onDelete(product) }
    }

    override fun getItemCount(): Int = productList.size

    fun updateProducts(products: List<Product>) {
        productList = products
        notifyDataSetChanged()
    }
}