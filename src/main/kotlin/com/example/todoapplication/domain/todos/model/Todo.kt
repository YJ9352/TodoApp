package com.example.todoapplication.domain.todos.model

import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.todos.common.TodoStatus
import com.example.todoapplication.domain.todos.dto.response.TodoResponse
import jakarta.persistence.*
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: TodoStatus,

    @OneToMany(mappedBy = "todo", cascade = [CascadeType.ALL])
    var comments: MutableList<Comment> = mutableListOf()

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todoid")
    var todoid: Long? = null

}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        todoid = todoid!!,
        userName = userName,
        title = title,
        detail = detail,
        dateCreated = dateCreated,
        status = TodoStatus.FALSE,
    )
}
