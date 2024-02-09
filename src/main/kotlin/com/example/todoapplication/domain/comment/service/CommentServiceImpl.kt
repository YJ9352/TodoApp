package com.example.todoapplication.domain.comment.service

import com.example.todoapplication.domain.comment.dto.request.CommentRequest
import com.example.todoapplication.domain.comment.dto.response.CommentAllResponse
import com.example.todoapplication.domain.comment.dto.response.CommentResponse
import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.comment.model.toCommentAllResponse
import com.example.todoapplication.domain.comment.model.toCommentResponse
import com.example.todoapplication.domain.comment.repository.CommentRepository
import com.example.todoapplication.domain.exception.ModelNotFoundException
import com.example.todoapplication.domain.todo.repository.TodoRepository
import com.example.todoapplication.domain.user.repository.UserRepository
import com.example.todoapplication.infra.regex.RegexFunc
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
    private val regexFunc: RegexFunc
): CommentService {

    // 댓글 전체조회
    override fun getAllCommentList(todoId: Long): List<CommentAllResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        return todo.comments.map { it.toCommentAllResponse() }

    }

    // 댓글 개별조회
    override fun getCommentById(commentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        return comment.toCommentResponse()
    }

    // 댓글 작성 (선택한 할 일 저장유무 확인)
    @Transactional
    override fun createComment(
        todoId: Long,
        userId: Long,
        request: CommentRequest
    ): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException(userId)

        val comment = Comment(
            commentDetail = regexFunc.regexCommentDetail(request.commentDetail),
            todo = todo,
            user = user
        )

        return commentRepository.save(comment).toCommentResponse()
    }

    // 댓글 수정 (이름, 비밀번호 저장값과 일치시 수정)
    @Transactional
    override fun updateComment(
        commentId: Long,
        todoId: Long,
        userId: Long,
        request: CommentRequest
    ): CommentResponse {
        val user = userRepository.findByUserId(userId) ?: throw ModelNotFoundException(userId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)

        if (comment.user.userId == user.userId) {
            comment.commentDetail = regexFunc.regexCommentDetail(request.commentDetail)
            return comment.toCommentResponse()
        } else {
            throw ModelNotFoundException(userId)
        }
    }

    // 댓글 삭제 (이름, 비밀번호 저장값과 일치시 수정)
    @Transactional
    override fun deleteComment(commentId: Long, userId: Long) {
        val user = userRepository.findByUserId(userId) ?: throw ModelNotFoundException(userId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException(commentId)
        if (comment.user.userId == user.userId) commentRepository.delete(comment) else throw ModelNotFoundException(userId)
    }
}