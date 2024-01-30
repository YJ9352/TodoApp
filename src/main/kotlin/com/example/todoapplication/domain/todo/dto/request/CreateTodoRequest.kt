package com.example.todoapplication.domain.todo.dto.request

data class CreateTodoRequest(
    val userId: Long,
    val userName: String,
    val title: String,
    val detail: String?,
    val status: String,
)