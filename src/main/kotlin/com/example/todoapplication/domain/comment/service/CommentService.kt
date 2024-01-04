package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.*

interface CommentService {

    fun getAllCommentList(): List<CommentResponse>

    fun getCommentById(commentId: Long): CommentResponse

    fun createComment(todoId: Long, request: CreateCommentRequest): CommentReturn

    fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentReturn

    fun deleteComment(commentId: Long, request: DeleteCommentRequest)
}