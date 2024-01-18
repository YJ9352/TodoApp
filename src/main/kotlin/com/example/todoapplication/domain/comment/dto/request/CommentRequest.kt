package com.example.todoapplication.domain.comment.dto.request

data class CommentRequest (
    val userId: Long,
    val todoId: Long,
    val commentDetail: String,
)