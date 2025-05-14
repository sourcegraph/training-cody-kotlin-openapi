package com.sourcegraph.demo.app.controller

import com.sourcegraph.demo.app.config.SerializationConfig.configureContentNegotiation
import com.sourcegraph.demo.app.service.PetService
import com.sourcegraph.demo.openapi.generated.model.Pet
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PetControllerTest {
    private val mockService = mockk<PetService>()
    private val controller = PetController(mockService)

    @Test
    fun `GET random pet endpoint should return single pet`() {
        // Arrange
        val mockPet = createTestPet()
        every { mockService.getRandomPet() } returns mockPet

        // Act & Assert
        withTestApplication({
            // Use the same content negotiation configuration as production
            configureContentNegotiation()

            routing {
                route("/api/v3") {
                    controller.registerRoutes(this)
                }
            }
        }) {
            handleRequest(HttpMethod.Get, "/api/v3/pet/random").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertTrue(response.content!!.contains(mockPet.name))
                verify(exactly = 1) { mockService.getRandomPet() }
            }
        }
    }

    @Test
    fun `GET random pets with count should return multiple pets`() {
        // Arrange
        val mockPets = listOf(createTestPet(), createTestPet())
        every { mockService.getRandomPets(2) } returns mockPets

        // Act & Assert
        withTestApplication({
            // Use the same content negotiation configuration as production
            configureContentNegotiation()

            routing {
                route("/api/v3") {
                    controller.registerRoutes(this)
                }
            }
        }) {
            handleRequest(HttpMethod.Get, "/api/v3/pet/random/2").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                verify(exactly = 1) { mockService.getRandomPets(2) }
            }
        }
    }

    private fun createTestPet(): Pet {
        return Pet(
            name = "TestPet",
            photoUrls = listOf("https://example.com/test.jpg"),
            id = 1L,
            category = null,
            tags = emptyList(),
            status = Pet.Status.AVAILABLE
        )
    }
}