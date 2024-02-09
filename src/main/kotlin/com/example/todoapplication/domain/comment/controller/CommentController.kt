package com.example.todoapplication.domain.comment.controller

import com.example.todoapplication.domain.comment.dto.request.CommentRequest
import com.example.todoapplication.domain.comment.dto.response.CommentAllResponse
import com.example.todoapplication.domain.comment.dto.response.CommentResponse
import com.example.todoapplication.domain.comment.service.CommentServiceImpl
import com.example.todoapplication.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/todos/{todoId}/comments")
@RestController
class CommentController(
    private val commentService: CommentServiceImpl
) {

    // 전체조회
    @GetMapping()
    fun getCommentList(@PathVariable todoId: Long):ResponseEntity<List<CommentAllResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getAllCommentList(todoId))
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
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(commentService.createComment(todoId, userPrincipal.userId, request))
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @PathVariable todoId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(commentService.updateComment(commentId, todoId, userPrincipal.userId, request))
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable commentId: Long,
        @PathVariable todoId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Unit> {
        commentService.deleteComment(commentId, userPrincipal.userId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}