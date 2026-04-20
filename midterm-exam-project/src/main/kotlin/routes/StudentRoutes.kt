package com.example.routes

import com.example.plugins.studentDatabase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.studentRoutes() {
    get("/api/student/{id}") {
        val id = call.parameters["id"]

        if (id.isNullOrBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Missing student ID")
            return@get
        }

        val student = studentDatabase[id]

        if (student == null) {
            call.respond(HttpStatusCode.NotFound, "No student found for ID: $id")
            return@get
        }

        val safeStudent = student.copy(
            major = student.major ?: "Undecided"
        )

        call.respond(safeStudent)
    }
}