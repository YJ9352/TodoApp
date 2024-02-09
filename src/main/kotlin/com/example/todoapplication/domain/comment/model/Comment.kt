package com.example.todoapplication.domain.comment.model

import com.example.todoapplication.domain.comment.dto.response.CommentAllResponse
import com.example.todoapplication.domain.comment.dto.response.CommentResponse
import com.example.todoapplication.domain.todo.model.Todo
import com.example.todoapplication.domain.user.model.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment(

    @Column(name = "commentdetail")
    var commentDetail: String,

    @ManyToOne
    @JoinColumn(name = "todoid", nullable = false)
    var todo: Todo,

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    var user: UserEntity,

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    var commentId: Long? = null
}

fun Comment.toCommentResponse(): CommentResponse {
    return CommentResponse(
        userId = user.userId!!,
        todoId = todo.todoId!!,
        commentId = commentId!!,
        commentDetail = commentDetail
    )
}

fun Comment.toCommentAllResponse(): CommentAllResponse {
    return CommentAllResponse(
        todoId = todo.todoId!!,
        commentId = commentId!!,
        userName = user.userName,
        commentDetail = commentDetail
    )
}
