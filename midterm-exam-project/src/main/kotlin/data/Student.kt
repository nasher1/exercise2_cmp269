package com.example.data

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: String,
    val name: String,
    val major: String?,
    val accessLevel: Int
)