package com.example.todoapplication.domain.user.dto.request

data class SignInRequest(
    val userEmail: String,
    val userPassword: String,
    val role: String,
)
