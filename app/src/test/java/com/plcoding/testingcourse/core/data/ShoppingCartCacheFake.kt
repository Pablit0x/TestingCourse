package com.plcoding.testingcourse.core.data

import com.plcoding.testingcourse.core.domain.Product
import com.plcoding.testingcourse.core.domain.ShoppingCartCache

class ShoppingCartCacheFake : ShoppingCartCache {

    private val items = mutableListOf<Product>()
    override fun saveCart(items: List<Product>) {
        this.items.addAll(items)
    }

    override fun loadCart(): List<Product> {
     return this.items
    }

    override fun clearCart() {
     this.items.clear()
    }
}