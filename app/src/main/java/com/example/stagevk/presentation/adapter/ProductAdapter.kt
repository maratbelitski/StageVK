package com.example.stagevk.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.stagevk.domain.entities.Product

class ProductAdapter : ListAdapter<Product, ViewHolder>(ProductDiffUtils()) {

    var onClickProduct: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ProductHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ProductHolder) {
            holder.binding(getItem(position), onClickProduct)
        }
    }
}