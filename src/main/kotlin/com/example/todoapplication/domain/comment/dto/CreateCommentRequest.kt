package com.example.todoapplication.domain.comment.dto

data class CreateCommentRequest (
    val commentName: String,
    val commentPassword: String,
    val commentContents: String,
)