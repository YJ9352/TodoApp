package com.example.todoapplication.domain.todo.service

import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.comment.repository.CommentRepository
import com.example.todoapplication.domain.exception.ModelNotFoundException
import com.example.todoapplication.domain.todo.common.TodoStatus
import com.example.todoapplication.domain.todo.dto.request.CreateTodoRequest
import com.example.todoapplication.domain.todo.dto.request.UpdateTodoRequest
import com.example.todoapplication.domain.todo.dto.response.TodoResponse
import com.example.todoapplication.domain.todo.dto.response.TodoWithCommentResponse
import com.example.todoapplication.domain.todo.model.Todo
import com.example.todoapplication.domain.todo.model.toTodoResponse
import com.example.todoapplication.domain.todo.model.toTodoWithCommentResponse
import com.example.todoapplication.domain.todo.repository.TodoRepository
import com.example.todoapplication.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository
): TodoService {

    // 투두 리스트 전체조회(내림차순)
    override fun getAllTodoList(): List<TodoResponse> {
        return todoRepository
            .findAll()
            .sortedByDescending { it.dateCreated }.map { it.toTodoResponse() }
    }

    // 투두리스트 개별조회 (연관댓글 추가)
    @Transactional
    override fun getTodoById(todoId: Long): TodoWithCommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        val comments: List<Comment> = commentRepository.findByTodo(todo)
        todo.comments.addAll(comments)

        return todo.toTodoWithCommentResponse()
    }

    // 투두리스트 작성
    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        val user = userRepository.findByUserId(request.userId) ?: throw ModelNotFoundException(request.userId)

        return todoRepository.save(
            Todo(
                userName = request.userName,
                title = request.title,
                detail = request.detail,
                dateCreated = LocalDateTime.now(),
                status = TodoStatus.FALSE,
                user = user
            )
        ).toTodoResponse()
    }

    // 투두리스트 수정
    @Transactional
    override fun updateTodo(userId: Long, todoId: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        if (todo.user.userId == userId) {
            val (userName, title, detail) = request

            todo.userName = userName
            todo.title = title
            todo.detail = detail

            return todoRepository.save(todo).toTodoResponse()
        } else {
            throw ModelNotFoundException(userId)
        }
    }

    // 투두리스트 삭제
    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        todoRepository.delete(todo)
    }

    // 할일 완료처리 여부 (기본값 FALSE)
    // status가 FALSE면 실행시 TRUE로 변경, TRUE면 FALSE로 변경 가능하게 수정
    @Transactional
    override fun updateStatus(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        todo.status = if (todo.status == TodoStatus.FALSE) TodoStatus.TRUE else TodoStatus.FALSE

        return todoRepository.save(todo).toTodoResponse()
    }
}