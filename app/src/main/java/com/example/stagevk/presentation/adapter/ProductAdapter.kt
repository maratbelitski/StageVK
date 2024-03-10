package com.example.stagevk.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.stagevk.domain.entities.Category
import com.example.stagevk.domain.entities.ListItems
import com.example.stagevk.domain.entities.Product
import java.lang.IllegalStateException

class ProductAdapter : ListAdapter<ListItems, ViewHolder>(ProductDiffUtils()) {

    var onClickProduct: ((Product) -> Unit)? = null
    var onClickCategory: ((Category) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Product -> 0
            is Category -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> ProductHolder.from(parent)
            1 -> CategoryHolder.from(parent)
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if (item is Product && holder is ProductHolder) {
            holder.bind(item, onClickProduct)
        }
        if (item is Category && holder is CategoryHolder) {
            holder.bind(item, onClickCategory)
        }
    }
}