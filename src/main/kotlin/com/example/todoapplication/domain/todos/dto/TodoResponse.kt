package com.example.todoapplication.domain.todos.dto

import java.time.LocalDate

data class TodoResponse(
    val id: Long,
    val userName: String,
    val title: String,
    val detail: String?,
    val dateCreated: LocalDate,
)
