package com.example.stagevk.domain.entities

import com.google.gson.annotations.SerializedName
data class Product(
    @SerializedName("id") var id : Int,
    @SerializedName("title") var title : String,
    @SerializedName("description") var description : String,
    @SerializedName("price") var price : Int,
//    @SerializedName("discountPercentage") var discountPercentage : Double,
    @SerializedName("rating") var rating : Double,
//    @SerializedName("stock") var stock : Int,
    @SerializedName("brand") var brand : String,
    @SerializedName("category") var category : String,
    @SerializedName("thumbnail") var thumbnail : String,
    @SerializedName("images") var images : ArrayList<String>
)