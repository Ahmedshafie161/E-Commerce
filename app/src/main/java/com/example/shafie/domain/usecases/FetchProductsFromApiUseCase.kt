package com.example.shafie.domain.usecases

import com.example.shafie.data.repo.ProductRepo
import com.example.shafie.data.repo.ProductRepoImpl
import com.example.shafie.domain.model.Product
import com.example.shafie.domain.model.toDomain

class FetchProductsFromApiUseCase(
    private val productRepo: ProductRepo = ProductRepoImpl()
) {
    suspend operator fun invoke(): List<Product> {
//        if (email.isEmpty())
//            throw UiException.EmptyEmail
//        if (password.isEmpty())
//            throw UiException.EmptyPassword

        return productRepo.getResultFromApi()?.map {
            it.toDomain()
        } ?: emptyList()

    }
}

sealed class UiException : Throwable() {
    object EmptyEmail : UiException()
    object EmptyPassword : UiException()
}


