package com.example.stagevk.domain.entities
import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @SerializedName("products") var products : List<Product>,
)
