package com.example.stagevk.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stagevk.domain.entities.ListItems
import com.example.stagevk.databinding.ProductItemBinding
import com.example.stagevk.domain.entities.Product

class ProductHolder(private val binding: ProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): ProductHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val productItemBinding = ProductItemBinding.inflate(layoutInflater)
            return ProductHolder(productItemBinding)
        }
    }

    fun bind(product: Product, click: ((Product) -> Unit)?) {
        with(binding) {
            Glide.with(itemView.context)
                .load(product.thumbnail)
                .into(ivProductImage)

            tvTitle.text = product.title
            tvDescription.text = product.description
        }
        itemView.setOnClickListener {
            click?.invoke(product)
        }
    }
}