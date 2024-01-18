package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.request.CommentRequest
import com.example.todoapplication.domain.comment.dto.response.CommentResponse
import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.comment.model.toCommentResponse
import com.example.todoapplication.domain.comment.repository.CommentRepository
import com.example.todoapplication.domain.exception.ModelNotFoundException
import com.example.todoapplication.domain.todo.repository.TodoRepository
import com.example.todoapplication.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository
): CommentService {

    // 댓글 전체조회
    override fun getAllCommentList(): List<CommentResponse> {
        return commentRepository.findAll().map { it.toCommentResponse() }
    }

    // 댓글 개별조회
    override fun getCommentById(commentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        return comment.toCommentResponse()
    }

    // 댓글 작성 (선택한 할 일 저장유무 확인)
    @Transactional
    override fun createComment(
        userId: Long,
        todoId: Long,
        request: CommentRequest
    ): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException(userId)

        return commentRepository.save(
            Comment(
                user = user,
                todo = todo,
                commentDetail = request.commentDetail
            )
        ).toCommentResponse()
    }

    // 댓글 수정 (이름, 비밀번호 저장값과 일치시 수정)
    @Transactional
    override fun updateComment(
        userId: Long,
        todoId: Long,
        commentId: Long,
        request: CommentRequest
    ): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)

        comment.commentDetail = request.commentDetail
        return comment.toCommentResponse()
    }

    // 댓글 삭제 (이름, 비밀번호 저장값과 일치시 수정)
    @Transactional
    override fun deleteComment(commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        commentRepository.delete(comment)
    }
}