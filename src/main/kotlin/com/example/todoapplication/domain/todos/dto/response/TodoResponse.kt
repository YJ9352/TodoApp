package com.example.todoapplication.domain.todos.dto.response

import com.example.todoapplication.domain.todos.common.TodoStatus
import java.time.LocalDateTime

data class TodoResponse(
    val todoid: Long,
    val userName: String,
    val title: String,
    val detail: String?,
    val dateCreated: LocalDateTime,
    val status: TodoStatus,
)
