package com.example.todoapplication.domain.user.service

import com.example.todoapplication.domain.user.dto.request.SignInRequest
import com.example.todoapplication.domain.user.dto.request.SignUpRequest
import com.example.todoapplication.domain.user.dto.request.UserUpdateRequest
import com.example.todoapplication.domain.user.dto.request.WithdrawRequest
import com.example.todoapplication.domain.user.dto.response.SignInResponse
import com.example.todoapplication.domain.user.dto.response.UserResponse

interface UserService {

    fun signUp(userEamil: String, request: SignUpRequest): UserResponse

    fun signIn(request: SignInRequest): SignInResponse

    fun withdraw(userEamil: String, request: WithdrawRequest)

    fun userUpdate(userEamil: String, request: UserUpdateRequest): UserResponse
}