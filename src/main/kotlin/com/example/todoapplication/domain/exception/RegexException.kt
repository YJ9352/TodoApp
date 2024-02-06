package com.example.todoapplication.domain.exception

data class RegexException(val form: String, val text: String) : RuntimeException(
    "$form $text 다시 확인해 주세요."
)