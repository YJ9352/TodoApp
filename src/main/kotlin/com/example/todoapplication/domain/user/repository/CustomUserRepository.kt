package com.example.todoapplication.domain.user.repository

import com.example.todoapplication.domain.user.model.UserEntity

interface CustomUserRepository {
    fun searchUserListByName(userName: String): List<UserEntity>
}