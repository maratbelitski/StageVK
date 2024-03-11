package com.example.stagevk.domain.usecases

import com.example.stagevk.domain.ProductRepository
import com.example.stagevk.domain.entities.ServerResponse
import io.reactivex.rxjava3.core.Single

class GetAllProductsUseCase(private val repository: ProductRepository) {

    operator fun invoke(product: Int, limit: Int): Single<ServerResponse>? {
        return repository.getAllProducts(product, limit)
    }
}