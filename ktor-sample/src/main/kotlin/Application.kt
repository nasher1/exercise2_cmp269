package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import io.ktor.server.http.content.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    routing {

        get("/") {
            call.respondText("Server is online at Lehman College.")
        }

        get("/greet/{name}") {
            val name = call.parameters["name"]
            call.respondText("Hello, $name! Welcome to CMP 269.")
        }

        get("/grade/{studentId}") {

            val grades = mapOf(
                "123" to 95,
                "456" to 82
            )

            val studentId = call.parameters["studentId"]
            val grade = grades[studentId]

            if (grade != null) {
                call.respondText("Student $studentId has grade: $grade")
            } else {
                call.respond(
                    HttpStatusCode.NotFound,
                    "Student not found"
                )
            }
        }

        staticResources("/static", "static")

        get("/api/stock/{symbol}") {
            val symbol = call.parameters["symbol"] ?: "UNKNOWN"
            val stock = Stock(symbol, 150.25)
            call.respond(stock)
        }
    }
}


@Serializable
data class Stock(
    val symbol: String,
    val price: Double
)