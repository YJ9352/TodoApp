package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.request.CommentRequest
import com.example.todoapplication.domain.comment.dto.response.CommentResponse

interface CommentService {

    // 댓글 전체조회
    fun getAllCommentList(): List<CommentResponse>

    // 댓글 개별조회
    fun getCommentById(commentId: Long): CommentResponse

    // 댓글 작성
    fun createComment(userId: Long, todoId: Long, request: CommentRequest): CommentResponse

    // 댓글 수정
    fun updateComment(userId: Long, todoId: Long,commentId: Long, request: CommentRequest): CommentResponse

    // 댓글 삭제
    fun deleteComment(commentId: Long)
}