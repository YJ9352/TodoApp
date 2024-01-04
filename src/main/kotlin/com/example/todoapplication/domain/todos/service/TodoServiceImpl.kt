package com.example.todoapplication.domain.todos.service

import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.comment.model.toRes
import com.example.todoapplication.domain.comment.repository.CommentRepository
import com.example.todoapplication.domain.exception.ModelNotFoundException
import com.example.todoapplication.domain.todos.dto.*
import com.example.todoapplication.domain.todos.model.Todo
import com.example.todoapplication.domain.todos.model.toResponse
import com.example.todoapplication.domain.todos.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository
): TodoService {

    // 투두 리스트 전체조회(내림차순)
    override fun getAllTodoList(): List<TodoResponse> {
        return todoRepository.findAll().sortedByDescending { it.dateCreated }.map { it.toResponse() }
    }

    // 투두리스트 개별조회 (연관댓글 추가)
    @Transactional
    override fun getTodoById(todoId: Long, commentId: Long): TodoWithCommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)

        val comments: List<Comment> = commentRepository.findByTodoId(todoId)
        todo.comments.addAll(comments)

        return TodoWithCommentResponse(
            id = todo.id!!,
            userName = todo.userName,
            title = todo.title,
            detail = todo.detail,
            dateCreated = todo.dateCreated,
            status = todo.status,
            comments = comments.map { it.toRes() }
        )
    }

    // 투두리스트 작성
    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        return todoRepository.save(
            Todo(
                userName = request.userName,
                title = request.title,
                detail = request.detail,
                dateCreated = LocalDateTime.now(),
                status = false
                )
        ).toResponse()
    }

    // 투두리스트 수정
    @Transactional
    override fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        val (userName, title, detail) = request

        todo.userName = userName
        todo.title = title
        todo.detail = detail

        return todoRepository.save(todo).toResponse()
    }

    // 투두리스트 삭제
    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        todoRepository.delete(todo)
    }

    // 할일 완료처리 여부
    @Transactional
    override fun updateStatus(todoId: Long, request: UpdateStatus): Boolean {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        val (status) = request

        todo.status = status

        return if (!status) true else false
    }
}