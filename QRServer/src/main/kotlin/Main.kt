import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import io.ktor.http.ContentType
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondBytes
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        routing {
            get("/") {
                call.respondText(
                    "QRServer is running. Open /qr?text=Hello to generate a QR code.",
                    ContentType.Text.Plain
                )
            }

            get("/qr") {
                val text = call.request.queryParameters["text"]
                    ?.takeIf { it.isNotBlank() }
                    ?: "Hello from QRServer"

                call.respondBytes(generateQrPng(text), ContentType.Image.PNG)
            }
        }
    }.start(wait = true)
}

private fun generateQrPng(text: String, size: Int = 300): ByteArray {
    val matrix = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size, size)
    val output = ByteArrayOutputStream()

    ImageIO.write(MatrixToImageWriter.toBufferedImage(matrix), "PNG", output)
    return output.toByteArray()
}