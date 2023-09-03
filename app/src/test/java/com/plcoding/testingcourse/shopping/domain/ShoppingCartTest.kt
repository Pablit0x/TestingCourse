package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
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

        val priceSum = cart.getTotalCost()

        assertThat(priceSum).isEqualTo(15.0)
    }

    @Test
    fun `Add product with negative quantity, throws Exception`(){
        val product = Product(
            id = 0,
            name = "Ice cream",
            price = 2.0
        )

        assertFailure {
            cart.addProduct(product = product, quantity = -1)
        }
    }


}