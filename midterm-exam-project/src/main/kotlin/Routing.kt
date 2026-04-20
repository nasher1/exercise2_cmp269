package com.example

import io.ktor.server.application.*
import io.ktor.server.routing.*
import com.example.routes.staticRoutes
import com.example.routes.studentRoutes
import com.example.routes.qrRoutes

fun Application.configureRouting() {
    routing {
        staticRoutes()
        studentRoutes()
        qrRoutes()
    }
}
