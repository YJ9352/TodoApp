package com.example.todoapplication.domain.comment.dto.request

data class CreateCommentRequest (
    val commentName: String,
    val commentPassword: String,
    val commentDetail: String,
)