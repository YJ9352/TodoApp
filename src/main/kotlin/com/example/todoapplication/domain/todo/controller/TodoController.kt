package com.example.todoapplication.domain.todo.controller

import com.example.todoapplication.domain.todo.dto.request.CreateTodoRequest
import com.example.todoapplication.domain.todo.dto.request.UpdateTodoRequest
import com.example.todoapplication.domain.todo.dto.response.TodoResponse
import com.example.todoapplication.domain.todo.dto.response.TodoWithCommentResponse
import com.example.todoapplication.domain.todo.service.TodoService
import com.example.todoapplication.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService,
) {

    // 전체조회
    @GetMapping
    fun getTodoList():ResponseEntity<List<TodoResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAllTodoList())
    }

    // 개별조회 (연관댓글 추가)
    @GetMapping("/{todoId}")
    fun getTodoByUserId(@PathVariable todoId: Long): ResponseEntity<TodoWithCommentResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodoById(todoId))
    }

    // 글 작성
    @PostMapping
    fun createTodo(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody createTodoRequest: CreateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(userPrincipal.userId, createTodoRequest))
    }

    // 글 수정
    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.updateTodo(todoId, userPrincipal.userId, updateTodoRequest))
    }

    // 삭제
    @DeleteMapping("/{todoId}")
    fun deleteTodo(
        @PathVariable todoId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId, userPrincipal.userId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    // 할일 완료처리 여부
    @PatchMapping("/{todoId}")
    fun updateStatus(
        @PathVariable todoId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.updateStatus(todoId, userPrincipal.userId))
    }
}