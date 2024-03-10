package com.example.stagevk.data.retrofit

import com.example.stagevk.domain.entities.ServerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/products")
    fun loadProducts(@Query("skip") skip: Int, @Query("limit") limit: Int)
    : Single<ServerResponse>?

    @GET("/products/category/{category}")
    fun loadOneCategory(@Path("category") lap: String): Single<ServerResponse>?

    @GET("/products/categories")
    fun loadCategories(): Single<MutableList<String>>?
}