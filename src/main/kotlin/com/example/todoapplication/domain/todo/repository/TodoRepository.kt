package com.example.todoapplication.domain.todo.repository

import com.example.todoapplication.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo, Long> {
}