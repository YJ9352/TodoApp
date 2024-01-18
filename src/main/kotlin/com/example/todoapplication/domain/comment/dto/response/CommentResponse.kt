package com.example.todoapplication.domain.comment.dto.response

data class CommentResponse (
    val commentId: Long,
    val commentName: String,
    val commentPassword: String,
    val commentDetail: String,
)