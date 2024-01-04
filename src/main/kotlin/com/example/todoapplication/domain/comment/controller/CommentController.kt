package com.example.todoapplication.domain.comment.controller

import com.example.todoapplication.domain.comment.dto.CommentResponse
import com.example.todoapplication.domain.comment.dto.CommentReturn
import com.example.todoapplication.domain.comment.dto.CreateCommentRequest
import com.example.todoapplication.domain.comment.dto.UpdateCommentRequest
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

    @GetMapping()
    fun getCommentList():ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getAllCommentList())
    }

    @GetMapping("/{commentId}")
    fun getComment(@PathVariable commentId: Long): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentById(commentId))
    }

    @PostMapping()
    fun createComment(@PathVariable todoId: Long,
                      @RequestBody @Valid createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentReturn> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(todoId, createCommentRequest))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @RequestBody @Valid updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentReturn> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(commentId, updateCommentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<Unit> {
        commentService.deleteComment(commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}