package com.plcoding.testingcourse.core.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.plcoding.testingcourse.core.domain.AnalyticsLogger
import com.plcoding.testingcourse.core.domain.LogParam
import com.plcoding.testingcourse.core.domain.Product
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.HttpException

class ProductRepositoryImplTest {
    private lateinit var repo: ProductRepositoryImpl
    private lateinit var productApi: ProductApi
    private lateinit var analyticsLogger: AnalyticsLogger

    @BeforeEach
    fun setUp() {
        productApi = mockk()
        analyticsLogger = mockk(relaxed = true)
        repo = ProductRepositoryImpl(productApi, analyticsLogger)
    }

    @Test
    fun `Response error, exception is logged`() = runBlocking {
        coEvery {
            productApi.purchaseProducts(any())
        } throws mockk<HttpException> {
            every { code() } returns 404
            every { message() } returns "Http Exception Message"
        }

        val result = repo.purchaseProducts(listOf())
        assertThat(result.isFailure).isTrue()

        verify {
            analyticsLogger.logEvent(
                "http_error", LogParam("code", 404), LogParam("message", "Http Exception Message")
            )
        }
    }
}