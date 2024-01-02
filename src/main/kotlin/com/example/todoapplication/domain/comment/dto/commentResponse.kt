package com.example.todoapplication.domain.comment.dto

data class commentResponse (
    val commentId: Long,
    val commentName: String,
    val commentPassword: String,
    val commentContents: String,
)