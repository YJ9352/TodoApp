package com.example.todoapplication.domain.user.dto.response

import com.example.todoapplication.domain.user.common.UserRole

data class UserResponse(
    val userId: Long,
    val userEmail: String,
    val userPassword: String,
    val userName: String,
    val role: UserRole,
)
