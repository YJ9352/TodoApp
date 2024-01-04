package com.example.todoapplication.domain.comment.repository

import com.example.todoapplication.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {

    fun findByTodoId(todoId: Long): List<Comment>
}