package com.example.todoapplication.domain.todos.dto

import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val userName: String,
    val title: String,
    val detail: String?,
    val dateCreated: LocalDateTime,
)
