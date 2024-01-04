package com.example.todoapplication.domain.todos.dto

import com.example.todoapplication.domain.comment.dto.CommentReturnResponse
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val userName: String,
    val title: String,
    val detail: String?,
    val dateCreated: LocalDateTime,
    val status: Boolean,
    val comments: List<CommentReturnResponse>
)
