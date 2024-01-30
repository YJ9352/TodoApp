package com.example.todoapplication.domain.todo.dto.response

import com.example.todoapplication.domain.todo.common.TodoStatus
import java.time.LocalDateTime

data class TodoResponse(
    val todoId: Long,
    val userName: String,
    val title: String,
    val detail: String?,
    val dateCreated: LocalDateTime,
    val status: String,
)
