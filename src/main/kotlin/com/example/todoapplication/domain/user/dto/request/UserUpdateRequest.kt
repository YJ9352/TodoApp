package com.example.todoapplication.domain.user.dto.request

import com.example.todoapplication.domain.user.common.UserStatus

data class UserUpdateRequest(
    val userEmail: String,
    val userPassword: String,
    val newUserPassword: String,
    val userName: String,
    val role: UserStatus,
)