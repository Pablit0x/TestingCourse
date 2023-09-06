package com.plcoding.testingcourse.part6

import com.google.firebase.auth.FirebaseAuth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrderServiceTest {

    private lateinit var orderService: OrderService
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var emailClient: EmailClient

    @BeforeEach
    fun setUp() {
        firebaseAuth = mockk(relaxed = true)
        emailClient = mockk(relaxed = true)
        orderService = OrderService(
            auth = firebaseAuth, emailClient = emailClient
        )
    }

    @Test
    fun `Place order with non-anonymous user - email sent`() {

        every { firebaseAuth.currentUser?.isAnonymous } returns false

        val productName = "banana"
        val email = "mail@op.gg"

        orderService.placeOrder(customerEmail = email, productName = productName)

        verify {
            emailClient.send(
                email = Email(
                    subject = "Order Confirmation",
                    content = "Thank you for your order of $productName.",
                    recipient = email
                )
            )
        }
    }
}