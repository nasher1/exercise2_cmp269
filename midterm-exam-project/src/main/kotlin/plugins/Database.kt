package com.example.plugins

import com.example.data.Student
import io.ktor.server.application.*
import java.util.concurrent.ConcurrentHashMap

val studentDatabase = ConcurrentHashMap<String, Student>()

fun Application.configureStudentDatabase() {
    // Pre-populate with sample students
    studentDatabase["student1"] = Student(
        id = "student1",
        name = "John Doe",
        major = "Computer Science",
        accessLevel = 1
    )
    
    studentDatabase["student2"] = Student(
        id = "student2",
        name = "Jane Smith",
        major = null, // Undecided
        accessLevel = 2
    )
    
    studentDatabase["student3"] = Student(
        id = "student3",
        name = "Bob Johnson",
        major = "Mathematics",
        accessLevel = 3
    )
}