package com.example.todoapplication.domain.user.service

import com.example.todoapplication.domain.exception.ModelNotFoundException
import com.example.todoapplication.domain.user.common.UserStatus
import com.example.todoapplication.domain.user.dto.request.SignInRequest
import com.example.todoapplication.domain.user.dto.request.SignUpRequest
import com.example.todoapplication.domain.user.dto.request.UserUpdateRequest
import com.example.todoapplication.domain.user.dto.request.WithdrawRequest
import com.example.todoapplication.domain.user.dto.response.SignInResponse
import com.example.todoapplication.domain.user.dto.response.UserResponse
import com.example.todoapplication.domain.user.dto.response.UserUpdateResponse
import com.example.todoapplication.domain.user.model.UserEntity
import com.example.todoapplication.domain.user.model.toUserResponse
import com.example.todoapplication.domain.user.repository.UserRepository
import com.example.todoapplication.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : UserService {

    @Transactional
    override fun signUp(userEamil: String, request: SignUpRequest): UserResponse {
        userRepository.findByUserEmail(userEamil)?.let { throw IllegalStateException("Email is already in use") }

        return userRepository.save(
            UserEntity(
                userEmail = request.userEmail,
                userPassword = passwordEncoder.encode(request.userPassword),
                userName = request.userName,
                role = UserStatus.USER,
            )
        ).toUserResponse()
    }

    @Transactional
    override fun signIn(request: SignInRequest): SignInResponse {
        val user = userRepository.findByUserEmail(request.userEmail)
            ?.takeIf { passwordEncoder.matches(request.userPassword, it.userPassword) }
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")

        return SignInResponse(
            accessToken = jwtPlugin.generateAccessToken(userEmail = user.userEmail)
        )
    }

    override fun withdraw(userEamil: String, request: WithdrawRequest) {
        val userPass = userRepository.findByUserEmail(userEamil)
            ?.takeIf { passwordEncoder.matches(request.userPassword, it.userPassword) }
            ?: throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")

        userRepository.delete(userPass)
    }

    @Transactional
    override fun userUpdate(userId: Long, userEamil: String, request: UserUpdateRequest): UserUpdateResponse {
        val user = userRepository.findByUserEmail(userEamil)
            ?.takeIf { passwordEncoder.matches(request.userPassword, it.userPassword) }
            ?: throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")

        user.userName = request.userName

        if (passwordEncoder.matches(request.userPassword, user.userPassword)) {
            user.userPassword = passwordEncoder.encode(request.newUserPassword)
        }

        userRepository.save(user)

        return UserUpdateResponse(user.userEmail, user.userName)
    }
}