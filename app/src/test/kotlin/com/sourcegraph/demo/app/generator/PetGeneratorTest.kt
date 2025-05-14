package com.sourcegraph.demo.app.generator

import com.sourcegraph.demo.app.data.PetData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PetGeneratorTest {
    private val generator = PetGenerator()

    @Test
    fun `generateRandomPet should create valid pet with non-null properties`() {
        val pet = generator.generateRandomPet()

        assertNotNull(pet.id)
        assertNotNull(pet.name)
        assertNotNull(pet.category)
        assertNotNull(pet.photoUrls)
        assertNotNull(pet.tags)
        assertNotNull(pet.status)
    }

    @Test
    fun `generateRandomPet should use values from PetData`() {
        val pet = generator.generateRandomPet()

        assertTrue(PetData.petNames.contains(pet.name))
        assertTrue(PetData.categories.contains(pet.category))

        // Check that tags use names from commonTags
        pet.tags?.forEach { tag ->
            assertTrue(PetData.commonTags.contains(tag.name))
        }
    }

    @Test
    fun `generateRandomPet should create different pets on subsequent calls`() {
        val pet1 = generator.generateRandomPet()
        val pet2 = generator.generateRandomPet()

        // Use null-safe operators for comparison
        assertFalse(
            pet1.id == pet2.id &&
                    pet1.name == pet2.name &&
                    pet1.category?.id == pet2.category?.id
        )
    }
}
