package com.example.todoapplication.domain.todos.model

import com.example.todoapplication.domain.todos.dto.TodoResponse
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
    var status: Boolean = FALSE

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        userName = userName,
        title = title,
        detail = detail,
        dateCreated = dateCreated,
        status = status
    )
}