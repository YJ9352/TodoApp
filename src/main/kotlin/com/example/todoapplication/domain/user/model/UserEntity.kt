package com.example.todoapplication.domain.user.model

import com.example.todoapplication.domain.todo.model.Todo
import com.example.todoapplication.domain.user.common.UserStatus
import com.example.todoapplication.domain.user.dto.response.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Column(name = "useremail")
    var userEmail: String,

    @Column(name = "userpassword")
    var userPassword: String,

    @Column(name = "username")
    var userName: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: UserStatus,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val userTodoList: MutableList<Todo> = mutableListOf(),

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    val userId: Long = 0
}

fun UserEntity.toUserResponse(): UserResponse {
    return UserResponse(
        userId = userId!!,
        userEmail = userEmail,
        userPassword = userPassword,
        userName = userName,
        role = role,
    )
}