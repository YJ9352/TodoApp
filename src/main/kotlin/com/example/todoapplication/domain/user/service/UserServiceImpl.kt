package com.example.todoapplication.domain.user.service

import com.example.todoapplication.domain.exception.UserInformationNotFoundException
import com.example.todoapplication.domain.user.common.UserRole
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
    override fun emailCheck(userEamil: String) {
        val user = userRepository.findByUserEmail(userEamil)
        if (user != null) {
            throw UserInformationNotFoundException(userEamil, "이미 동일한 이메일로 계정이 있는 것 같습니다")
        } else {
            throw UserInformationNotFoundException(userEamil, "은 가입된 이메일이 아닙니다.")
        }
    }

    @Transactional
    override fun signUp(userEamil: String, request: SignUpRequest): UserResponse {
        val user = userRepository.findByUserEmail(userEamil)
            ?.let { throw UserInformationNotFoundException(userEamil, "이미 동일한 이메일로 계정이 있는 것 같습니다") }

        return userRepository.save(
            UserEntity(
                userEmail = request.userEmail,
                userPassword =
                if (request.userPassword == request.newUserPassword) passwordEncoder.encode(request.userPassword)
                else throw UserInformationNotFoundException(request.userPassword, "비밀번호 확인이 일치하지 않는 것 같습니다") ,
                userName = request.userName,
                role = UserRole.USER,
            )
        ).toUserResponse()
    }

    @Transactional
    override fun signIn(request: SignInRequest): SignInResponse {
        val user = userRepository.findByUserEmail(request.userEmail)
            ?.takeIf { passwordEncoder.matches(request.userPassword, it.userPassword) }
            ?: throw UserInformationNotFoundException(request.userPassword, "기존 비밀번호 및 이메일이 일치하지 않습니다")

        return SignInResponse(
            accessToken = jwtPlugin.generateAccessToken(
                userId = user.userId!!,
                userEmail = user.userEmail,
                role = user.role.name
            )
        )
    }

    override fun withdraw(userEamil: String, request: WithdrawRequest) {
        val userPass = userRepository.findByUserEmail(userEamil)
            ?.takeIf { passwordEncoder.matches(request.userPassword, it.userPassword) }
            ?: throw UserInformationNotFoundException(request.userPassword, "기존 비밀번호가 일치하지 않습니다")

        userRepository.delete(userPass)
    }

    @Transactional
    override fun userUpdate(userEamil: String, request: UserUpdateRequest): UserUpdateResponse {
        val user = userRepository.findByUserEmail(userEamil)
            ?.takeIf { passwordEncoder.matches(request.userPassword, it.userPassword) }
            ?: throw UserInformationNotFoundException(request.userPassword, "기존 비밀번호가 일치하지 않습니다")

        user.userName = request.userName

        if (passwordEncoder.matches(request.userPassword, user.userPassword)) {
            user.userPassword = passwordEncoder.encode(request.newUserPassword)
        }

        userRepository.save(user)

        return UserUpdateResponse(user.userEmail, user.userName)
    }
}