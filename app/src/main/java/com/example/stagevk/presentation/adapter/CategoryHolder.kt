package com.example.stagevk.presentation.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.stagevk.R
import com.example.stagevk.databinding.CategoryItemBinding
import com.example.stagevk.domain.entities.Category

class CategoryHolder(private val binding: CategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): CategoryHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val categoryItemBinding = CategoryItemBinding.inflate(layoutInflater)
            return CategoryHolder(categoryItemBinding)
        }
    }

    fun bind(category: Category, click: ((Category) -> Unit)?) {

        with(binding) {
        tvCategory.text = category.categoryName
        }
        itemView.setOnClickListener {
            click?.invoke(category)
        }
    }
}