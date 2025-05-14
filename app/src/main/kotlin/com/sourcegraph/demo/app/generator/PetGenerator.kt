package com.sourcegraph.demo.app.generator

import com.sourcegraph.demo.app.data.PetData
import com.sourcegraph.demo.openapi.generated.model.Pet
import com.sourcegraph.demo.openapi.generated.model.Tag
import kotlin.random.Random

// Add 'open' keyword to allow mocking
open class PetGenerator {

    fun generateRandomPet(): Pet {
        val name = PetData.petNames.random()
        val photoUrls = listOf("https://example.com/pet/${Random.nextLong(1, 1000)}.jpg")
        val id = Random.nextLong(1, 1000)
        val category = PetData.categories.random()

        val tags = PetData.commonTags.shuffled().take(2).map { tagName ->
            Tag(id = Random.nextLong(1, 100), name = tagName)
        }

        val status = when (Random.nextInt(3)) {
            0 -> Pet.Status.AVAILABLE
            1 -> Pet.Status.PENDING
            else -> Pet.Status.SOLD
        }

        return Pet(
            name = name,
            photoUrls = photoUrls,
            id = id,
            category = category,
            tags = tags,
            status = status
        )
    }
}