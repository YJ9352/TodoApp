package com.example.todoapplication.domain.todos.service

import com.example.todoapplication.domain.todos.dto.CreateTodoRequest
import com.example.todoapplication.domain.todos.dto.TodoResponse
import com.example.todoapplication.domain.todos.dto.UpdateTodoRequest
import com.example.todoapplication.domain.todos.model.TodoStatus

interface TodoService {

    fun getAllTodoList(): List<TodoResponse>

    fun getTodoById(todoId: Long): TodoResponse

    fun createTodo(request: CreateTodoRequest): TodoResponse

    fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse

    fun deleteTodo(todoId: Long)

    fun updateStatus(todoId: Long, status: TodoStatus): TodoResponse
}