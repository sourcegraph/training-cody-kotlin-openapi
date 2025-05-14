package com.sourcegraph.demo.app.config

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*

object SerializationConfig {
    /**
     * Configures content negotiation with Jackson for the application.
     * This is used by both the main application and tests to ensure consistent behavior.
     */
    fun Application.configureContentNegotiation() {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
                // Add any other Jackson configuration you need
            }
        }
    }
}
