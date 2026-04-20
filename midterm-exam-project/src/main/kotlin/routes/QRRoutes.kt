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

fun Route.qrRoutes() {
    get("/generate-id") {
        val sid = call.request.queryParameters["sid"]

        if (sid.isNullOrBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Missing sid query parameter")
            return@get
        }

        val student = studentDatabase[sid]

        if (student == null) {
            call.respond(HttpStatusCode.NotFound, "Student with ID '$sid' not found")
            return@get
        }

        val qrText = "ID=${student.id};ACCESS=${student.accessLevel}"

        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(qrText, BarcodeFormat.QR_CODE, 300, 300)

        val outputStream = ByteArrayOutputStream()
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)

        call.respondBytes(
            bytes = outputStream.toByteArray(),
            contentType = ContentType.Image.PNG
        )
    }
}