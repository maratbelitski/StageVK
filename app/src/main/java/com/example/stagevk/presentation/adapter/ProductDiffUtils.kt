package com.example.stagevk.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.stagevk.domain.entities.Product

class ProductDiffUtils : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}