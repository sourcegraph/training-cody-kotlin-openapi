package com.sourcegraph.demo.app.config

import com.sourcegraph.demo.app.controller.PetController
import io.ktor.application.*
import io.ktor.routing.*


/**
 * Configuration object for setting up API routing.
 * Contains extension function to configure routing for the application,
 * setting up API endpoints under the /api/v3 path.
 */
object RoutingConfig {
    /**
     * Configures routing for the application by setting up API endpoints.
     * @param petController The controller handling pet-related endpoints
     */
    fun Application.configureRouting(petController: PetController) {
        routing {
            route("/api/v3") {
                petController.registerRoutes(this)
            }
        }
    }
}
