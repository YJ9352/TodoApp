package com.example.todoapplication.domain.todos.dto.response

import com.example.todoapplication.domain.comment.dto.CommentReturnResponse
import com.example.todoapplication.domain.todos.common.TodoStatus
import java.time.LocalDateTime

data class TodoWithCommentResponse(
    val todoid: Long,
    val userName: String,
    val title: String,
    val detail: String?,
    val dateCreated: LocalDateTime,
    val status: TodoStatus,
    val comments: List<CommentReturnResponse>
)
