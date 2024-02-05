package com.example.todoapplication.domain.exception

data class ModelNotFoundException(val id: Long): RuntimeException(
    "not found with given $id"
)