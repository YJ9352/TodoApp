package com.example.todoapplication.domain.exception

data class ModelNotFoundException(val todoId: Long): RuntimeException(
    "not found with given id: $todoId"
)