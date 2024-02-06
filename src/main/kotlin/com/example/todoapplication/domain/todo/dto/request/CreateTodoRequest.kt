package com.example.todoapplication.domain.todo.dto.request

data class CreateTodoRequest(
    val title: String,
    val detail: String?
)