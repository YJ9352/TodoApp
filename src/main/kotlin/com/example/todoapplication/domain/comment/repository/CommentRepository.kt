package com.example.todoapplication.domain.comment.repository

import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.todos.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
    fun findByTodo(todo: Todo): List<Comment>
}