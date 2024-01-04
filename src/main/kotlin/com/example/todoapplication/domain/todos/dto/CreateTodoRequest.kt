package com.example.todoapplication.domain.todos.dto

data class CreateTodoRequest(
    val userName: String,
    val title: String,
    val detail: String?,
    val status: Boolean,
)