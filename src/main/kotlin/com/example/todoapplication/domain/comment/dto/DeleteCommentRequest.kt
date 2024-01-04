package com.example.todoapplication.domain.comment.dto

data class DeleteCommentRequest(
    val commentName: String,
    val commentPassword: String,
)
