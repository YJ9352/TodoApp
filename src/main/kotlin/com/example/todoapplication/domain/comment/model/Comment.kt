package com.example.todoapplication.domain.comment.model

import com.example.todoapplication.domain.comment.dto.CommentResponse
import com.example.todoapplication.domain.comment.dto.CommentReturnResponse
import com.example.todoapplication.domain.todos.model.Todo
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment(

    @Column(name = "commentname")
    var commentName: String,

    @Column(name = "commentpassword")
    var commentPassword: String,

    @Column(name = "commentcontents")
    var commentContents: String,

    @ManyToOne
    @JoinColumn(name = "todoid")
    var todo: Todo,

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    var commentid: Long? = null
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        commentId = commentid!!,
        commentName = commentName,
        commentPassword = commentPassword,
        commentContents = commentContents,
    )
}

fun Comment.toRes(): CommentReturnResponse {
    return CommentReturnResponse(
        commentName = this.commentName,
        commentContents = this.commentContents
    )
}
