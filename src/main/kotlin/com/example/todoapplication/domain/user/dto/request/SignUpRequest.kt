package com.example.todoapplication.domain.user.dto.request

import com.example.todoapplication.domain.user.common.UserStatus

data class SignUpRequest(
    val userEmail: String,
    val userPassword: String,
    val userName: String,
    val role: UserStatus,
)
