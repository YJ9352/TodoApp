package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.*

interface CommentService {

    // 댓글 전체조회
    fun getAllCommentList(): List<CommentResponse>

    // 댓글 개별조회
    fun getCommentById(commentId: Long): CommentResponse

    // 댓글 작성
    fun createComment(todoId: Long, request: CreateCommentRequest): CommentReturn

    // 댓글 수정
    fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentReturn

    // 댓글 삭제
    fun deleteComment(commentId: Long, request: DeleteCommentRequest)
}