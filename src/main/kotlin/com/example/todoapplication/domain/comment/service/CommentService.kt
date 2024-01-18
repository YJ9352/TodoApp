package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.request.CreateCommentRequest
import com.example.todoapplication.domain.comment.dto.request.DeleteCommentRequest
import com.example.todoapplication.domain.comment.dto.request.UpdateCommentRequest
import com.example.todoapplication.domain.comment.dto.response.CommentResponse
import com.example.todoapplication.domain.comment.dto.response.CommentReturnResponse

interface CommentService {

    // 댓글 전체조회
    fun getAllCommentList(): List<CommentResponse>

    // 댓글 개별조회
    fun getCommentById(commentId: Long): CommentResponse

    // 댓글 작성
    fun createComment(todoId: Long, request: CreateCommentRequest): CommentReturnResponse

    // 댓글 수정
    fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentReturnResponse

    // 댓글 삭제
    fun deleteComment(commentId: Long, request: DeleteCommentRequest)
}