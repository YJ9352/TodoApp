package com.example.todoapplication.domain.user.dto.request

import com.example.todoapplication.domain.user.common.UserRole

data class SignInRequest(
    val userEmail: String,
    val userPassword: String,
    val role: String,
)
