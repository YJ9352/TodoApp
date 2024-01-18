package com.example.todoapplication.domain.comment.dto.response

data class CommentResponse (
    val userId: Long,
    val todoId: Long,
    val commentId: Long,
    val commentDetail: String,
)