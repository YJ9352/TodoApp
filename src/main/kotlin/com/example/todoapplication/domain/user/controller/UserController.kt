package com.example.todoapplication.domain.user.controller

import com.example.todoapplication.domain.user.dto.request.SignInRequest
import com.example.todoapplication.domain.user.dto.request.SignUpRequest
import com.example.todoapplication.domain.user.dto.request.UserUpdateRequest
import com.example.todoapplication.domain.user.dto.response.SignInResponse
import com.example.todoapplication.domain.user.dto.response.UserResponse
import com.example.todoapplication.domain.user.dto.response.UserUpdateResponse
import com.example.todoapplication.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun searchUserList(@RequestParam(value = "userName") userName: String): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.searchUserList(userName))
    }

    // 이메일 중복체크
    @GetMapping
    fun emailCheck(userEamil: String): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.emailCheck(userEamil))
    }
    
    // 회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpRequest, userEmail: String): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(userEmail, request))
    }
    
    // 로그인 
    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<SignInResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signIn(request))
    }
    
    // 회원정보 수정

    @PostMapping("/userupdate")
    fun userUpdate(
        @RequestBody request: UserUpdateRequest,
        userEmail: String
    ): ResponseEntity<UserUpdateResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.userUpdate(userEmail, request))
    }
    
}