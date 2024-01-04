package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.*
import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.comment.model.toRes
import com.example.todoapplication.domain.comment.model.toResponse
import com.example.todoapplication.domain.comment.repository.CommentRepository
import com.example.todoapplication.domain.exception.ModelNotFoundException
import com.example.todoapplication.domain.todos.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository,
): CommentService {

    // 댓글 전체조회
    override fun getAllCommentList(): List<CommentResponse> {
        return commentRepository.findAll().map { it.toResponse() }
    }

    // 댓글 개별조회
    override fun getCommentById(commentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        return comment.toResponse()
    }

    // 댓글 작성 (선택한 할 일 저장유무 확인)
    @Transactional
    override fun createComment(todoId: Long, request: CreateCommentRequest): CommentReturnResponse {
        val todo = todoRepository.findById(todoId)
        val resComment = CommentReturnResponse(commentName = request.commentName, commentContents = request.commentContents)
        if (todo.isPresent) {
            commentRepository.save(
                Comment(
                    commentName = request.commentName,
                    commentPassword = request.commentPassword,
                    commentContents = request.commentContents,
                    todo = todo.get()
                )
            ).toResponse()
        } else {
            throw ModelNotFoundException(todoId)
        }
        return resComment
    }

    // 댓글 수정 (이름, 비밀번호 저장값과 일치시 수정)
    @Transactional
    override fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentReturnResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        val (commentName, commentPassword, commentContents) = request

        if (comment.commentPassword == request.commentPassword && comment.commentName == request.commentName) {
            comment.commentContents = commentContents

            return comment.toRes()

        } else {
            throw RuntimeException("Invalid comment credentials")
        }
    }

    // 댓글 삭제 (이름, 비밀번호 저장값과 일치시 수정)
    @Transactional
    override fun deleteComment(commentId: Long, request: DeleteCommentRequest) {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)

        if (comment.commentPassword == request.commentPassword && comment.commentName == request.commentName) {
            commentRepository.delete(comment)
        } else {
            throw RuntimeException("Invalid comment credentials")
        }
    }
}