package com.example.todoapplication.domain.todos.controller

import com.example.todoapplication.domain.todos.dto.request.CreateTodoRequest
import com.example.todoapplication.domain.todos.dto.request.UpdateTodoRequest
import com.example.todoapplication.domain.todos.dto.response.TodoResponse
import com.example.todoapplication.domain.todos.dto.response.TodoWithCommentResponse
import com.example.todoapplication.domain.todos.service.TodoService
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

@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService,
) {

    // 전체조회
    @GetMapping()
    fun getTodoList():ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAllTodoList())
    }

    // 개별조회 (연관댓글 추가)
    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long, commentId: Long): ResponseEntity<TodoWithCommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(todoId, commentId))
    }

    // 글 작성
    @PostMapping
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createTodoRequest))
    }

    // 글 수정
    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId, updateTodoRequest))
    }

    // 삭제
    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    // 할일 완료처리 여부
//    @PatchMapping("/{todoId}")
//    fun updateStatus(@PathVariable todoId: Long,
//                     @RequestBody status: TodoStatus
//    ): ResponseEntity<Boolean> {
//        return ResponseEntity
//            .status(HttpStatus.OK)
//            .body(todoService.updateStatus(todoId, UpdateStatus(true)))
//    }
}