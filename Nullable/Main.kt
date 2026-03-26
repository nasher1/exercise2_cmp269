// fun main() {
//     // Declare studentName (non-nullable) and middleName (nullable)
//     val studentName: String = "Shahd"
//     val middleName: String? = null

//     // Use Elvis operator ?: to provide a default value if middleName is null
//     println("Welcome, $studentName ${middleName ?: "No Middle Name"}!")
// }

// // Data class for Laptop
// data class Laptop(val brand: String, val ramGB: Int)

// // Extension function for Int
// fun Int.toLehmanGigabytes(): String = "$this GB (Lehman Standard)"

// fun main() {
//     // Create two Laptop instances
//     val laptop1 = Laptop("Dell", 16)
//     val laptop2 = Laptop("Apple", 32)

//     // Print their RAM using the extension function
//     println("${laptop1.brand} RAM: ${laptop1.ramGB.toLehmanGigabytes()}")
//     println("${laptop2.brand} RAM: ${laptop2.ramGB.toLehmanGigabytes()}")
// }

// Sealed class for Enrollment Status
sealed class EnrollmentStatus {
    data class Success(val courseCode: String) : EnrollmentStatus()
    data class Error(val message: String) : EnrollmentStatus()
    object Loading : EnrollmentStatus()
}

// Function to print the status
fun printStatus(status: EnrollmentStatus) {
    when (status) {
        is EnrollmentStatus.Success -> println("Enrolled in course: ${status.courseCode}")
        is EnrollmentStatus.Error -> println("Error occurred: ${status.message}")
        EnrollmentStatus.Loading -> println("Enrollment is in progress...")
    }
}

fun main() {
    val successStatus = EnrollmentStatus.Success("CMP-334")
    val errorStatus = EnrollmentStatus.Error("Network issue")

    printStatus(successStatus) // Handles Success
    printStatus(errorStatus)   // Handles Error
    printStatus(EnrollmentStatus.Loading) // Handles Loading
}