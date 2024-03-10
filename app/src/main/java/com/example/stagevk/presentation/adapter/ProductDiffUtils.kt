package com.example.stagevk.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.stagevk.domain.entities.Category
import com.example.stagevk.domain.entities.ListItems
import com.example.stagevk.domain.entities.Product

class ProductDiffUtils : DiffUtil.ItemCallback<ListItems>() {
    override fun areItemsTheSame(oldItem: ListItems, newItem: ListItems): Boolean {
        var result = false
        if (oldItem is Product && newItem is Product) {
            result = oldItem.id == newItem.id
        } else if (oldItem is Category && newItem is Category) {
            result = oldItem.id == newItem.id
        }
        return result
    }

    override fun areContentsTheSame(oldItem: ListItems, newItem: ListItems): Boolean {
        var result = false
        if (oldItem is Product && newItem is Product) {
            result = oldItem == newItem
        } else if (oldItem is Category && newItem is Category) {
            result = oldItem == newItem
        }
        return result
    }
}