package com.example.todoapplication.domain.todo.dto.request

import com.example.todoapplication.domain.todo.common.TodoStatus

data class CreateTodoRequest(
    val userName: String,
    val title: String,
    val detail: String?,
    val status: TodoStatus,
)