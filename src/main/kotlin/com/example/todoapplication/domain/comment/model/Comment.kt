package com.example.todoapplication.domain.comment.model

import com.example.todoapplication.domain.comment.dto.response.CommentResponse
import com.example.todoapplication.domain.todo.model.Todo
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment(

    @Column(name = "commentname")
    var commentName: String,

    @Column(name = "commentpassword")
    var commentPassword: String,

    @Column(name = "commentdetail")
    var commentDetail: String,

    @ManyToOne
    @JoinColumn(name = "todoid", nullable = false)
    var todo: Todo,

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    var commentid: Long? = null
}

fun Comment.toCommentResponse(): CommentResponse {
    return CommentResponse(
        commentId = commentid!!,
        commentName = commentName,
        commentPassword = commentPassword,
        commentDetail = commentDetail,
    )
}
