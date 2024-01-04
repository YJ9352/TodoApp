package com.example.todoapplication.domain.todos.service

import com.example.todoapplication.domain.todos.dto.CreateTodoRequest
import com.example.todoapplication.domain.todos.dto.TodoResponse
import com.example.todoapplication.domain.todos.dto.UpdateStatus
import com.example.todoapplication.domain.todos.dto.UpdateTodoRequest

interface TodoService {

    // 투두리스트 전체조회
    fun getAllTodoList(): List<TodoResponse>

    // 투두리스트 개별조회
    fun getTodoById(todoId: Long): TodoResponse

    // 투두리스트 작성
    fun createTodo(request: CreateTodoRequest): TodoResponse

    // 투두리스트 수정
    fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse

    // 투두리스트 삭제
    fun deleteTodo(todoId: Long)

    // 투두리스트 상태완료
    fun updateStatus(todoId: Long, request: UpdateStatus): Boolean
}