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
import com.example.todoapplication.infra.regex.RegexFunc
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val regexFunc: RegexFunc
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
    override fun createTodo(userId: Long, request: CreateTodoRequest): TodoResponse {
        val user = userRepository.findByUserId(userId) ?: throw ModelNotFoundException(userId)

        return todoRepository.save(
            Todo(
                userName = user.userName,
                title = regexFunc.regexTitle(request.title),
                detail = regexFunc.regexDetail(request.detail),
                dateCreated = LocalDateTime.now(),
                status = TodoStatus.FALSE,
                user = user
            )
        ).toTodoResponse()
    }

    // 투두리스트 수정
    @Transactional
    override fun updateTodo(todoId: Long, userId: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        val user = userRepository.findByUserId(userId) ?: throw ModelNotFoundException(userId)
        if (todo.user.userId == user.userId) {
            val (title, detail) = request

            todo.title = regexFunc.regexTitle(title)
            todo.detail = regexFunc.regexDetail(detail)

            return todoRepository.save(todo).toTodoResponse()
        } else {
            throw ModelNotFoundException(userId)
        }
    }

    // 투두리스트 삭제
    @Transactional
    override fun deleteTodo(todoId: Long, userId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        val user = userRepository.findByUserId(userId) ?: throw ModelNotFoundException(userId)
        if (todo.user.userId == user.userId) todoRepository.delete(todo) else throw ModelNotFoundException(todoId)
    }

    // 할일 완료처리 여부 (기본값 FALSE)
    // status가 FALSE면 실행시 TRUE로 변경, TRUE면 FALSE로 변경 가능하게 수정
    @Transactional
    override fun updateStatus(todoId: Long, userId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException(todoId)
        val user = userRepository.findByUserId(userId) ?: throw ModelNotFoundException(userId)

        if (todo.user.userId == user.userId) {
            todo.status = if (todo.status == TodoStatus.FALSE) TodoStatus.TRUE else TodoStatus.FALSE

            return todoRepository.save(todo).toTodoResponse()
        } else {
            throw ModelNotFoundException(userId)
        }
    }
}