package com.sourcegraph.demo.app

import com.github.trly.utils.Printer
import com.sourcegraph.demo.app.config.RoutingConfig.configureRouting
import com.sourcegraph.demo.app.config.SerializationConfig.configureContentNegotiation
import com.sourcegraph.demo.app.config.ServerConfig.configureServer
import com.sourcegraph.demo.app.controller.PetController
import com.sourcegraph.demo.app.service.PetService
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val printer = Printer("Starting Pet Store API Server...")
    printer.printMessage()

    // Initialize services and controllers
    val petService = PetService()
    val petController = PetController(petService)

    // Start Ktor server with modular configuration
    embeddedServer(Netty, port = 8080) {
        // Apply shared configurations
        configureContentNegotiation()
        configureServer()

        // Configure routing with our controller
        configureRouting(petController)
    }.start(wait = true)
}