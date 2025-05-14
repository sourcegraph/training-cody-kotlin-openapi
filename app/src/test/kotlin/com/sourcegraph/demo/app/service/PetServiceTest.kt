package com.sourcegraph.demo.app.service

import com.sourcegraph.demo.app.generator.PetGenerator
import com.sourcegraph.demo.openapi.generated.model.Pet
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PetServiceTest {
    private val mockGenerator = mockk<PetGenerator>()
    private val service = PetService(mockGenerator)

    @Test
    fun `getRandomPet should return pet from generator`() {
        // Arrange
        val mockPet = createTestPet()
        every { mockGenerator.generateRandomPet() } returns mockPet

        // Act
        val result = service.getRandomPet()

        // Assert
        assertEquals(mockPet, result)
        verify(exactly = 1) { mockGenerator.generateRandomPet() }
    }

    @Test
    fun `getRandomPets should return correct number of pets`() {
        // Arrange
        val mockPet = createTestPet()
        every { mockGenerator.generateRandomPet() } returns mockPet

        // Act
        val result = service.getRandomPets(3)

        // Assert
        assertEquals(3, result.size)
        verify(exactly = 3) { mockGenerator.generateRandomPet() }
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