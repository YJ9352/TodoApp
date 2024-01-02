package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.CreateCommentRequest
import com.example.todoapplication.domain.comment.dto.UpdateCommentRequest
import com.example.todoapplication.domain.comment.dto.CommentResponse

interface CommentService {

    fun getAllCommentList(): List<CommentResponse>

    fun getCommentById(commentId: Long): CommentResponse

    fun createComment(request: CreateCommentRequest): CommentResponse

    fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentResponse

    fun deleteComment(commentId: Long)
}