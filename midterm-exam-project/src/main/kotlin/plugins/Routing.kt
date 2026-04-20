package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import com.example.routes.staticRoutes
import com.example.routes.studentRoutes
import com.example.routes.qrRoutes

fun Application.configureRouting() {
    install(ContentNegotiation) {
        json()
    }
    
    routing {
        staticRoutes()
        studentRoutes()
        qrRoutes()
    }
}