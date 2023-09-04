package com.plcoding.testingcourse.core.data

import com.plcoding.testingcourse.core.domain.Product
import com.plcoding.testingcourse.core.domain.ProductRepository

class ProductRepositoryFake : ProductRepository {
    private val purchasedProducts = mutableListOf<Product>()
    var shouldResultError: Boolean = false
    override suspend fun purchaseProducts(products: List<Product>): Result<Unit> {

        products.forEach {
            purchasedProducts.add(it)
        }

        return if (shouldResultError) {
            Result.failure(Exception())
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun cancelPurchase(purchaseId: Int): Result<Unit> {

        purchasedProducts.removeIf{
            it.id == purchaseId
        }

        return if (shouldResultError) {
            Result.failure(Exception())
        } else {
            Result.success(Unit)
        }
    }
}