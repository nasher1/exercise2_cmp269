package com.example.routes

import com.example.plugins.studentDatabase
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.ByteArrayOutputStream

private fun createQrBytes(text: String): ByteArray {
    val matrix = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, 300, 300)
    val output = ByteArrayOutputStream()
    MatrixToImageWriter.writeToStream(matrix, "PNG", output)
    return output.toByteArray()
}

fun Route.qrRoutes() {
    get("/generate-id") {
        val sid = call.request.queryParameters["sid"]

        if (sid.isNullOrBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Please provide a student ID.")
            return@get
        }

        val student = studentDatabase[sid]
        if (student == null) {
            call.respond(HttpStatusCode.NotFound, "Student not found.")
            return@get
        }

        val qrText = "studentId=${student.id}|name=${student.name}|access=${student.accessLevel}"

        call.respondBytes(
            bytes = createQrBytes(qrText),
            contentType = ContentType.Image.PNG
        )
    }
}