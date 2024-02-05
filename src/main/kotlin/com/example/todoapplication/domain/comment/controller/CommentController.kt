package com.example.todoapplication.domain.comment.controller

import com.example.todoapplication.domain.comment.dto.request.CommentRequest
import com.example.todoapplication.domain.comment.dto.response.CommentResponse
import com.example.todoapplication.domain.comment.service.CommentServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/todos/{todoId}/comments")
@RestController
class CommentController(
    private val commentService: CommentServiceImpl
) {

    // 전체조회
    @GetMapping()
    fun getCommentList(@PathVariable todoId: Long):ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getAllCommentList())
    }

    // 개별조회
    @GetMapping("/{commentId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    fun getComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentById(commentId))
    }

    // 댓글 작성
    @PostMapping()
    fun createComment(
        @PathVariable todoId: Long,
        @RequestHeader("userid") userId: Long,
        @RequestBody request: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(todoId, userId, request))
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @PathVariable todoId: Long,
        @RequestHeader("userId") userId: Long,
        @RequestBody request: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentId, todoId, userId, request))
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable commentId: Long,
        @PathVariable todoId: Long,
        @RequestHeader("userid") userId: Long,
        @RequestBody request: CommentRequest
    ): ResponseEntity<Unit> {
        commentService.deleteComment(commentId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}