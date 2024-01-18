package com.example.todoapplication.domain.todo.dto

import com.example.todoapplication.domain.todo.common.TodoStatus

data class UpdateStatus(
    val status: TodoStatus = TodoStatus.TRUE,
)
