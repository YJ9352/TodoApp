package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.CommentResponse
import com.example.todoapplication.domain.comment.dto.CreateCommentRequest
import com.example.todoapplication.domain.comment.dto.UpdateCommentRequest
import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.comment.model.toResponse
import com.example.todoapplication.domain.comment.repository.CommentRepository
import com.example.todoapplication.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
): CommentService {

    override fun getAllCommentList(): List<CommentResponse> {
        return commentRepository.findAll().map { it.toResponse() }
    }

    // todo와 연결된 항목 보이게 하기
    override fun getCommentById(commentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        return comment.toResponse()
    }

    // 여기서부터 비밀번호 대조
    @Transactional
    override fun createComment(request: CreateCommentRequest): CommentResponse {
        return commentRepository.save(
            Comment(
                commentName = request.commentName,
                commentPassword = request.commentPassword,
                commentContents = request.commentContents,
                )
        ).toResponse()
    }

    @Transactional
    override fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        val (commentName, commentPassword, commentContents) = request

        comment.commentName = commentName
        comment.commentPassword = commentPassword
        comment.commentContents = commentContents

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun deleteComment(commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        commentRepository.delete(comment)
    }
}