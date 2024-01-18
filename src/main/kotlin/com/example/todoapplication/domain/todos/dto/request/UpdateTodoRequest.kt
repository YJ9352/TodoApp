package com.example.todoapplication.domain.todos.dto.request

import com.example.todoapplication.domain.todos.common.TodoStatus

data class UpdateTodoRequest(
    val userName: String,
    val title: String,
    val detail: String?,
    val status: TodoStatus,
)