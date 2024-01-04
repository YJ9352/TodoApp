package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.*
import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.comment.model.toRes
import com.example.todoapplication.domain.comment.model.toResponse
import com.example.todoapplication.domain.comment.repository.CommentRepository
import com.example.todoapplication.domain.exception.GlobalExceptionHandler
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

    override fun getAllCommentList(): List<CommentResponse> {
        return commentRepository.findAll().map { it.toResponse() }
    }

    override fun getCommentById(commentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        return comment.toResponse()
    }

    @Transactional
    override fun createComment(todoId: Long, request: CreateCommentRequest): CommentReturn {
        val todo = todoRepository.findById(todoId)
        val resComment = CommentReturn(commentName = request.commentName, commentContents = request.commentContents)
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

    @Transactional
    override fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentReturn {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        val (commentName, commentPassword, commentContents) = request

//        comment.commentName = commentName
//        comment.commentPassword = commentPassword

        if (comment.commentPassword == request.commentPassword && comment.commentName == request.commentName) {
            comment.commentContents = commentContents

            return comment.toRes()

        } else {
            throw RuntimeException("Invalid comment credentials")
        }
    }

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