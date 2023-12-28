package com.example.todoapplication.domain.todos.repository

import com.example.todoapplication.domain.todos.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo, Long> {
}