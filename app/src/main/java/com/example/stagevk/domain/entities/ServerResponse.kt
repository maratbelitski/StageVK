package com.example.stagevk.domain.entities
import com.example.stagevk.domain.entities.Product
import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @SerializedName("products") var products : List<Product>,
//    @SerializedName("total") var total : Int,
//    @SerializedName("skip") var skip : Int,
//    @SerializedName("limit") var limit : Int
)
