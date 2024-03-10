package com.example.stagevk.domain.usecases

import com.example.stagevk.domain.ProductRepository
import com.example.stagevk.domain.entities.Category
import com.example.stagevk.domain.entities.ServerResponse
import io.reactivex.rxjava3.core.Single

class GetOneCategoryUseCase(private val repository: ProductRepository) {

    operator fun invoke(category: String): Single<ServerResponse>? {
        return repository.getOneCategory(category)
    }
}