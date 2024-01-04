package com.example.todoapplication.domain.todos.model

import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.comment.model.toRes
import com.example.todoapplication.domain.todos.dto.TodoResponse
import com.example.todoapplication.domain.todos.dto.TodoWithCommentResponse
import jakarta.persistence.*
import java.lang.Boolean.FALSE
import java.time.LocalDateTime

@Entity
@Table(name = "todos")
class Todo(

    @Column(name = "username")
    var userName: String,

    @Column(name = "title")
    var title: String,

    @Column(name = "detail")
    var detail: String? = null,

    @Column(name = "datecreated")
    val dateCreated: LocalDateTime = LocalDateTime.now(),

    @Column(name = "status")
    var status: Boolean = FALSE,

    @OneToMany(mappedBy = "todo", cascade = [CascadeType.ALL])
    var comments: MutableList<Comment> = mutableListOf()

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        userName = userName,
        title = title,
        detail = detail,
        dateCreated = dateCreated,
        status = status,
    )
}

fun Todo.todoWithCommentResponse(): TodoWithCommentResponse {
    return TodoWithCommentResponse(
        id = id!!,
        userName = userName,
        title = title,
        detail = detail,
        dateCreated = dateCreated,
        status = status,
        comments = comments.map { it.toRes() }
    )
}