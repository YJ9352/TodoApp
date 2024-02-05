package com.example.todoapplication.domain.todo.model

import com.example.todoapplication.domain.comment.model.Comment
import com.example.todoapplication.domain.todo.common.TodoStatus
import com.example.todoapplication.domain.todo.dto.response.TodoResponse
import com.example.todoapplication.domain.user.model.UserEntity
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

    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "userid")
    val user: UserEntity,

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todoid")
    val todoId: Long? = null

}

fun Todo.toTodoResponse(): TodoResponse {
    return TodoResponse(
        todoId = todoId!!,
        userName = userName,
        title = title,
        detail = detail,
        dateCreated = dateCreated,
        status = status.name,
    )
}
