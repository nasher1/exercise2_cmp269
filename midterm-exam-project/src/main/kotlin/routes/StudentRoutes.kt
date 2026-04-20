package com.example.routes

import com.example.data.Student
import com.example.plugins.studentDatabase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun Route.studentRoutes() {
    get("/api/student/{id}") {
        val studentId = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
        
        val student = studentDatabase[studentId]
        if (student == null) {
            return@get call.respond(HttpStatusCode.NotFound, "Student with ID '$studentId' not found")
        }
        
        // Use Elvis operator for null safety
        val safeStudent = student.copy(major = student.major ?: "Undecided")
        call.respond(safeStudent)
    }
}