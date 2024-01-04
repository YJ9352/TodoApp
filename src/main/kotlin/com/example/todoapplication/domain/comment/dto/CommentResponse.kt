package com.example.todoapplication.domain.comment.dto

data class CommentResponse (
    val commentId: Long,
    val commentName: String,
    val commentPassword: String,
    val commentContents: String,
)