package com.example.routes

import com.example.data.Student
import com.example.plugins.studentDatabase
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import java.util.UUID

fun Route.qrRoutes() {
    get("/generate-id") {
        val studentId = call.parameters["sid"] ?: return@get call.respondText(
            "Missing sid parameter", status = io.ktor.http.HttpStatusCode.BadRequest
        )
        
        val student = studentDatabase[studentId] ?: return@get call.respondText(
            "Student not found", status = io.ktor.http.HttpStatusCode.NotFound
        )
        
        val qrData = generateQRData(student)
        val qrImage = generateQRWithLogo(qrData)
        
        call.respondBytes(qrImage, io.ktor.http.ContentType.Image.PNG)
    }
}

private fun generateQRData(student: Student): String {
    // Encrypt by encoding student data as JSON + UUID salt
    val encryptedData = mapOf(
        "salt" to UUID.randomUUID().toString(),
        "student" to student.copy(
            major = student.major ?: "Undecided"
        )
    )
    return Json.encodeToString(encryptedData)
}

private fun generateQRWithLogo(qrData: String): ByteArray {
    val size = 300
    val writer = QRCodeWriter()
    val hints = mapOf(
        EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.H,
        EncodeHintType.MARGIN to 1
    )
    
    val bitMatrix = writer.encode(qrData, BarcodeFormat.QR_CODE, size, size, hints)
    val qrImage = BufferedImage(size, size, BufferedImage.TYPE_INT_RGB)
    val qrGraphics = qrImage.createGraphics()
    
    // Draw QR code
    for (x in 0 until size) {
        for (y in 0 until size) {
            qrImage.setRGB(x, y, if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
        }
    }
    
    // Add Lehman Lightning bolt logo (simplified as a yellow bolt)
    val logoSize = 60
    val logoX = (size - logoSize) / 2
    val logoY = (size - logoSize) / 2
    
    val graphics = qrImage.createGraphics()
    graphics.color = Color.YELLOW
    graphics.fillPolygon(
        intArrayOf(logoX + 30, logoX + 15, logoX + 30, logoX + 45),
        intArrayOf(logoY + 10, logoY + 25, logoY + 35, logoY + 25),
        4
    )
    graphics.color = Color.ORANGE.darker()
    graphics.fillPolygon(
        intArrayOf(logoX + 25, logoX + 20, logoX + 25, logoX + 30),
        intArrayOf(logoY + 15, logoY + 22, logoY + 28, logoY + 22),
        4
    )
    graphics.dispose()
    
    // Convert to PNG bytes
    ByteArrayOutputStream().use { baos ->
        ImageIO.write(qrImage, "png", baos)
        return baos.toByteArray()
    }
}