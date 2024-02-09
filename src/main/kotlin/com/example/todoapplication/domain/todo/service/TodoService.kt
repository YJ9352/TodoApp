package com.example.todoapplication.domain.todo.service

import com.example.todoapplication.domain.todo.dto.request.CreateTodoRequest
import com.example.todoapplication.domain.todo.dto.request.UpdateTodoRequest
import com.example.todoapplication.domain.todo.dto.response.TodoResponse
import com.example.todoapplication.domain.todo.dto.response.TodoWithCommentResponse

interface TodoService {

    // 투두리스트 전체조회
    fun getAllTodoList(): List<TodoResponse>

    // 투두리스트 개별조회
    fun getTodoById(todoId: Long): TodoWithCommentResponse

    // 투두리스트 작성
    fun createTodo(userId: Long, request: CreateTodoRequest): TodoResponse

    // 투두리스트 수정
    fun updateTodo(todoId: Long, userId: Long, request: UpdateTodoRequest): TodoResponse

    // 투두리스트 삭제
    fun deleteTodo(todoId: Long, userId: Long)

    // 투두리스트 상태완료
    fun updateStatus(todoId: Long, userId: Long): TodoResponse
}