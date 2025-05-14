package com.sourcegraph.demo.app.config

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*


/**
 * Server configuration for the application.
 * Configures serialization and CORS settings for the Ktor server.
 *
 * Serialization is configured to use Jackson with indented output.
 * CORS is configured to allow OPTIONS and GET methods from any host.
 */
object ServerConfig {

    /**
     * Configures the server with serialization and CORS settings.
     * This is the main configuration function that sets up the application.
     */
    fun Application.configureServer() {
        configureSerialization()
        configureCORS()
    }

    /**
     * Configures serialization for the application using Jackson.
     * Sets up content negotiation with indented output formatting.
     */
    private fun Application.configureSerialization() {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
    }

    /**
     * Configures CORS settings for the application.
     * Allows OPTIONS and GET methods, and accepts requests from any host.
     * Sets up Access-Control-Allow-Origin header.
     */
    private fun Application.configureCORS() {
        install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Get)
            header(HttpHeaders.AccessControlAllowOrigin)
            anyHost()
        }
    }
}
