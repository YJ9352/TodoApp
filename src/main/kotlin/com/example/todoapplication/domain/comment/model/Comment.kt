package com.example.todoapplication.domain.comment.model

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
    var commentId: Long = 0L
}

fun Comment.toCommentResponse(): CommentResponse {
    return CommentResponse(
        userId = user.userId,
        todoId = todo.todoId,
        commentId = commentId,
        commentDetail = commentDetail
    )
}
