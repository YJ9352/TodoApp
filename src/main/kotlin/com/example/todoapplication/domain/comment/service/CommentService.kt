package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.CreateCommentRequest
import com.example.todoapplication.domain.comment.dto.UpdateCommentRequest
import com.example.todoapplication.domain.comment.dto.CommentResponse
import com.example.todoapplication.domain.comment.dto.CommentReturn

interface CommentService {

    fun getAllCommentList(): List<CommentResponse>

    fun getCommentById(commentId: Long): CommentResponse

    fun createComment(todoId: Long, request: CreateCommentRequest): CommentReturn

    fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentReturn

    fun deleteComment(commentId: Long)
}