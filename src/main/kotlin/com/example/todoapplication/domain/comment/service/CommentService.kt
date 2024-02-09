package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.request.CommentRequest
import com.example.todoapplication.domain.comment.dto.response.CommentAllResponse
import com.example.todoapplication.domain.comment.dto.response.CommentResponse

interface CommentService {

    // 댓글 전체조회
    fun getAllCommentList(todoId: Long): List<CommentAllResponse>

    // 댓글 개별조회
    fun getCommentById(commentId: Long): CommentResponse

    // 댓글 작성
    fun createComment(todoId: Long, userId: Long, request: CommentRequest): CommentResponse

    // 댓글 수정
    fun updateComment(commentId: Long, todoId: Long, userId: Long, request: CommentRequest): CommentResponse

    // 댓글 삭제
    fun deleteComment(commentId: Long, userId: Long)
}