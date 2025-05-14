package com.sourcegraph.demo.app.data

import com.sourcegraph.demo.openapi.generated.model.Category


/**
 * Contains static data for pets including sample names, categories, and common tags.
 * Used for generating test data and providing default values for the pet store application.
 */
object PetData {
    val petNames = listOf("Max", "Bella", "Luna", "Charlie", "Lucy", "Cooper", "Daisy", "Milo", "Zoe", "Rocky")
    val categories = listOf(
        Category(id = 1, name = "Dogs"),
        Category(id = 2, name = "Cats"),
        Category(id = 3, name = "Birds"),
        Category(id = 4, name = "Fish"),
        Category(id = 5, name = "Reptiles")
    )
    val commonTags = listOf("cute", "friendly", "trained", "playful", "young")
}
