// Exercise 1: WebResponse Data Class
data class WebResponse(
    val statusCode: Int,
    val statusMessage: String,
    val body: String? // Nullable
)

fun exercise1() {
    val successResponse = WebResponse(200, "OK", """{"message":"All good"}""")
    val notFoundResponse = WebResponse(404, "Not Found", null)

    println("Success Response: $successResponse")
    println("Not Found Response: $notFoundResponse")
}

// Exercise 2: Status code interpreter
fun describeStatus(code: Int): String {
    return when (code) {
        in 200..299 -> "Success: The request was fulfilled."
        in 400..499 -> "Client Error: Check your URL or parameters."
        in 500..599 -> "Server Error: The Lehman Server is having trouble."
        else -> "Unknown status code."
    }
}

fun exercise2() {
    val codes = listOf(201, 404, 503, 123)
    for (code in codes) {
        println("HTTP $code -> ${describeStatus(code)}")
    }
}

// Exercise 3: Simple server routing
fun routeRequest(path: String, user: String?): String {
    return when (path) {
        "/home" -> "Welcome to the Lehman Homepage, ${user ?: "Guest"}!"
        "/grades" -> if (user != null) {
            "Loading grades for $user..."
        } else {
            "Error: Unauthorized access to grades."
        }
        else -> "404: Path $path not found."
    }
}

fun exercise3() {
    val users = listOf<String?>(null, "Shahd")
    val paths = listOf("/home", "/grades", "/profile")

    for (user in users) {
        for (path in paths) {
            println("User=$user, Path=$path -> ${routeRequest(path, user)}")
        }
    }
}

fun main() {
    println("----- Exercise 1 -----")
    exercise1()
    println("\n----- Exercise 2 -----")
    exercise2()
    println("\n----- Exercise 3 -----")
    exercise3()
}