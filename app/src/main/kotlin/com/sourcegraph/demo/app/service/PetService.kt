package com.sourcegraph.demo.app.service

import com.sourcegraph.demo.app.generator.PetGenerator
import com.sourcegraph.demo.openapi.generated.model.Pet

/**
 * Service class for generating random pets.
 *
 * @property petGenerator The generator used to create random pet instances
 */
open class PetService(private val petGenerator: PetGenerator = PetGenerator()) {
    /**
     * Returns a randomly generated pet.
     *
     * @return A randomly generated [Pet] instance
     */
    fun getRandomPet(): Pet {
        return petGenerator.generateRandomPet()
    }

    /**
     * Returns a list of randomly generated pets.
     *
     * @param count The number of pets to generate
     * @return A list containing [count] randomly generated [Pet] instances
     */
    fun getRandomPets(count: Int): List<Pet> {
        return (1..count).map { getRandomPet() }
    }
}