package com.capstone.pawpal.dataclass

// Register data class
data class RegisterDataAccount(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)

// Login data class
data class LoginDataAccount(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)

// Response detail
data class ResponseDetail(
    val success: Boolean,
    val message: String
)

// Response login
data class ResponseLogin(
    val idToken: String,
    val email: String,
    val refreshToken: String,
    val expiresIn: String,
    val localId: String,
    val registered: Boolean
)

// Cat breed data class
data class CatBreed(
    val id: Int,
    val name: String,
    val description: String
)

// Symptom data class
data class Symptom(
    val id: Int,
    val name: String,
    val description: String
)

// Image upload response
data class ImageUploadResponse(
    val success: Boolean,
    val message: String,
    val imageUrl: String
)
