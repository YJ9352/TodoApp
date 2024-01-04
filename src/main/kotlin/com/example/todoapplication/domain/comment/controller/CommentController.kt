package com.example.todoapplication.domain.comment.controller

import com.example.todoapplication.domain.comment.dto.*
import com.example.todoapplication.domain.comment.service.CommentServiceImpl
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/todos/{todoId}/comments")
@RestController
class CommentController(
    private val commentService: CommentServiceImpl
) {

    // 전체조회
    @GetMapping()
    fun getCommentList():ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getAllCommentList())
    }

    // 개별조회
    @GetMapping("/{commentId}")
    fun getComment(@PathVariable commentId: Long): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentById(commentId))
    }

    // 댓글 작성
    @PostMapping()
    fun createComment(@PathVariable todoId: Long,
                      @RequestBody @Valid createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentReturn> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(todoId, createCommentRequest))
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @RequestBody @Valid updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentReturn> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(commentId, updateCommentRequest))
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long,
                      @RequestBody @Valid DeleteCommentRequest: DeleteCommentRequest
    ): ResponseEntity<Unit> {
        commentService.deleteComment(commentId, DeleteCommentRequest)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}