package com.example.todoapplication.domain.todos.dto

import com.example.todoapplication.domain.todos.common.TodoStatus

data class UpdateStatus(
    val status: TodoStatus,
)
