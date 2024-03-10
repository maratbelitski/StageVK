package com.example.stagevk.domain.entities

import java.util.UUID

data class Category(
    val categoryName: String = "",
    val id: String = UUID.randomUUID().toString()
) : ListItems
