package com.example.todoapplication.domain.exception

import com.example.todoapplication.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(UserInformationNotFoundException::class)
    fun userInformationNotFoundException(e: UserInformationNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(RegexException::class)
    fun regexException(e: RegexException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(message = e.message))
    }
}