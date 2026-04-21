// package com.example.routes

// import com.example.plugins.studentDatabase
// import com.google.zxing.BarcodeFormat
// import com.google.zxing.client.j2se.MatrixToImageWriter
// import com.google.zxing.qrcode.QRCodeWriter
// import io.ktor.http.*
// import io.ktor.server.application.*
// import io.ktor.server.response.*
// import io.ktor.server.routing.*
// import java.io.ByteArrayOutputStream

// private fun createQrBytes(text: String): ByteArray {
//     val matrix = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, 300, 300)
//     val output = ByteArrayOutputStream()
//     MatrixToImageWriter.writeToStream(matrix, "PNG", output)
//     return output.toByteArray()
// }

// fun Route.qrRoutes() {
//     get("/generate-id") {
//         val sid = call.request.queryParameters["sid"]

//         if (sid.isNullOrBlank()) {
//             call.respond(HttpStatusCode.BadRequest, "Please provide a student ID.")
//             return@get
//         }

//         val student = studentDatabase[sid]
//         if (student == null) {
//             call.respond(HttpStatusCode.NotFound, "Student not found.")
//             return@get
//         }

//         val qrText = "studentId=${student.id}|name=${student.name}|access=${student.accessLevel}"

//         call.respondBytes(
//             bytes = createQrBytes(qrText),
//             contentType = ContentType.Image.PNG
//         )
//     }
// }

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
import java.nio.file.Paths

fun saveQRCode(content: String, fileName: String) {
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 300, 300)
    val path = Paths.get(fileName)
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path)
}

fun generateQRCodeStream(content: String): ByteArrayOutputStream {
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 300, 300)
    val outputStream = ByteArrayOutputStream()
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)
    return outputStream
}

fun Route.qrRoutes() {

    // Lab route: /qr?text=GoLightning
    get("/qr") {
        val text = call.request.queryParameters["text"]

        if (text.isNullOrBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Missing text query parameter")
            return@get
        }

        val outputStream = generateQRCodeStream(text)

        call.response.header(HttpHeaders.ContentType, "image/png")
        call.respondBytes(
            bytes = outputStream.toByteArray(),
            contentType = ContentType.Image.PNG
        )
    }

    // Project route: /generate-id?sid=student1
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
        val outputStream = generateQRCodeStream(qrText)

        call.response.header(HttpHeaders.ContentType, "image/png")
        call.respondBytes(
            bytes = outputStream.toByteArray(),
            contentType = ContentType.Image.PNG
        )
    }
}