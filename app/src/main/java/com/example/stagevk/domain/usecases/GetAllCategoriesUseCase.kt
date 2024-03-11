package com.example.stagevk.domain.usecases

import com.example.stagevk.domain.ProductRepository
import io.reactivex.rxjava3.core.Single

class GetAllCategoriesUseCase(private val repository: ProductRepository) {

    operator fun invoke(): Single<MutableList<String>>? {
        return repository.getAllCategories()
    }
}