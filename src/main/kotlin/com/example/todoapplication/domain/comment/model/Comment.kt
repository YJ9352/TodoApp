package com.example.todoapplication.domain.comment.model

import com.example.todoapplication.domain.comment.dto.CommentResponse
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
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var commentid: Long? = null
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        commentId = commentid!!,
        commentName = commentName,
        commentPassword = commentPassword,
        commentContents = commentContents
    )
}