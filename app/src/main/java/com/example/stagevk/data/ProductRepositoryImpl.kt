package com.example.stagevk.data

import com.example.stagevk.data.retrofit.ApiFactory
import com.example.stagevk.domain.ProductRepository
import com.example.stagevk.domain.entities.ServerResponse
import io.reactivex.rxjava3.core.Single

object ProductRepositoryImpl : ProductRepository {

    private val api = ApiFactory.apiService

    override fun getAllProducts(product: Int, limit: Int): Single<ServerResponse>? {
        return api.loadProducts(product, limit)
    }

    override fun getAllCategories(): Single<MutableList<String>>? {
        return api.loadCategories()
    }

    override fun getOneCategory(category: String): Single<ServerResponse>? {
        return api.loadOneCategory(category)
    }
}