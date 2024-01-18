package com.example.todoapplication.domain.todo.dto.response

import com.example.todoapplication.domain.comment.dto.response.CommentResponse
import com.example.todoapplication.domain.todo.common.TodoStatus
import java.time.LocalDateTime

data class TodoWithCommentResponse(
    val todoid: Long,
    val userName: String,
    val title: String,
    val detail: String?,
    val dateCreated: LocalDateTime,
    val status: TodoStatus,
    val comments: List<CommentResponse>
)
