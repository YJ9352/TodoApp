package com.example.todoapplication.domain.todo.dto.request

data class UpdateTodoRequest(
    val title: String,
    val detail: String?
)