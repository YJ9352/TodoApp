package com.example.todoapplication.domain.exception

data class UserInformationNotFoundException(val form: String, val text: String): RuntimeException(
    "$form 과(와) $text, 다시 확인해주세요."
)