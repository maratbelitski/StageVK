package com.example.stagevk.domain

import com.example.stagevk.domain.entities.ServerResponse
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun getAllProducts(product:Int, limit:Int): Single<ServerResponse>?
}