package com.example.todoapplication.domain.user.dto.response

data class UserResponse(
    val userId: Long,
    val userEmail: String,
    val userPassword: String,
    val userName: String,
    val role: String,
)
