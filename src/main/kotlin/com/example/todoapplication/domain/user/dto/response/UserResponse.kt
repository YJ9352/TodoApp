package com.example.todoapplication.domain.user.dto.response

import com.example.todoapplication.domain.user.common.UserStatus

data class UserResponse(
    val userId: Long,
    val userEmail: String,
    val userPassword: String,
    val userName: String,
    val role: UserStatus,
)
