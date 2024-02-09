package com.example.todoapplication.domain.comment.dto.response

data class CommentAllResponse(
    val todoId: Long,
    val commentId: Long,
    val userName: String,
    val commentDetail: String,
)
