package com.example.todoapplication.domain.todo.service

import com.example.todoapplication.domain.todo.dto.request.CreateTodoRequest
import com.example.todoapplication.domain.todo.dto.request.UpdateTodoRequest
import com.example.todoapplication.domain.todo.dto.response.TodoResponse
import com.example.todoapplication.domain.todo.dto.response.TodoWithCommentResponse

interface TodoService {

    // 투두리스트 전체조회
    fun getAllTodoList(): List<TodoResponse>

    // 투두리스트 개별조회
    fun getTodoById(todoId: Long, commentId: Long): TodoWithCommentResponse

    // 투두리스트 작성
    fun createTodo(request: CreateTodoRequest): TodoResponse

    // 투두리스트 수정
    fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse

    // 투두리스트 삭제
    fun deleteTodo(todoId: Long)

    // 투두리스트 상태완료
    fun updateStatus(todoId: Long): TodoResponse
}