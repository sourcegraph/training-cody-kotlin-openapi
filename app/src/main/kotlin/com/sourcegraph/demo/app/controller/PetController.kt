package com.sourcegraph.demo.app.controller

import com.sourcegraph.demo.app.service.PetService
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*


/**
 * Controller class for handling pet-related HTTP endpoints.
 * Provides routes for retrieving random pets.
 *
 * @property petService Service for managing pet operations
 */
class PetController(private val petService: PetService) {
    /**
     * Registers HTTP routes for pet-related endpoints.
     * Sets up routes for retrieving random pets.
     *
     * @param routing The route configuration to add endpoints to
     */
    fun registerRoutes(routing: Route) {
        routing.get("/pet/random") {
            handleGetRandomPet(this.call)
        }

        routing.get("/pet/random/{count}") {
            handleGetRandomPets(this.call)
        }
    }

    /**
     * Handles HTTP request for retrieving a single random pet.
     *
     * @param call The application call to handle and respond to
     */
    private suspend fun handleGetRandomPet(call: ApplicationCall) {
        call.respond(petService.getRandomPet())
    }

    /**
     * Handles HTTP request for retrieving multiple random pets.
     *
     * @param call The application call to handle and respond to
     */
    private suspend fun handleGetRandomPets(call: ApplicationCall) {
        val count = call.parameters["count"]?.toIntOrNull() ?: 1
        call.respond(petService.getRandomPets(count))
    }
}
