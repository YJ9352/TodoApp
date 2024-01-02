package com.example.todoapplication.domain.comment.dto

data class UpdateCommentRequest (
    val commentName: String,
    val commentPassword: String,
    val commentContents: String,
)