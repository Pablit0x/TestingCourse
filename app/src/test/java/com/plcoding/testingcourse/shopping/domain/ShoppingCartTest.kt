package com.plcoding.testingcourse.shopping.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class ShoppingCartTest {
    private lateinit var cart: ShoppingCart

    @BeforeEach
    fun setup() {
        cart = ShoppingCart()
    }

    @Test
    fun `Add multiple products, total price sum is correct`() {
        val product = Product(
            id = 0,
            name = "Apple",
            price = 5.0
        )

        cart.addProduct(product = product, quantity = 3)

        assertThat(cart.getTotalCost()).isEqualTo(15.0)
    }


}