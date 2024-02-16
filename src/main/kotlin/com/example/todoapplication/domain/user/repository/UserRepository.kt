package com.example.todoapplication.domain.user.repository

import com.example.todoapplication.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long>, CustomUserRepository {
    fun findByUserId(userId: Long): UserEntity?
    fun findByUserEmail(userEmail: String): UserEntity?
}