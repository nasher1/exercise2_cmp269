package com.example

import com.example.routes.qrRoutes
import com.example.routes.staticRoutes
import com.example.routes.studentRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        staticRoutes()
        studentRoutes()
        qrRoutes()
    }
}