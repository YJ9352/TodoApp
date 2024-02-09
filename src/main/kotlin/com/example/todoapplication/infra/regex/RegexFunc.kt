package com.example.todoapplication.infra.regex

import com.example.todoapplication.domain.exception.RegexException
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class RegexFunc {

    // 메일주소
    fun regexUserEmail(userEmail: String): String {
        val email = "^([a-zA-Z0-9+-_.]{3,30})+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$"
        if (!Pattern.matches(email, userEmail))
            throw RegexException(userEmail, "이메일 형식이 올바르지 않습니다.") else return userEmail
    }

    // 비밀번호
    fun regexPassword(userPassword: String): String {
        val pass = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[.!@#%^&*])[a-zA-Z0-9.!@#%^&*]{4,20}\$"
        if (!Pattern.matches(pass, userPassword))
            throw RegexException(userPassword, "비밀번호 형식이 올바르지 않습니다.") else return userPassword
    }

    // 제목
    fun regexTitle(usertitle: String): String {
        val title = "^[\\s\\S]{1,500}\$"
        if (!Pattern.matches(title, usertitle)) throw RegexException(title, "글자수는 1~500 사이로 작성해주세요.") else return usertitle
    }

    // 내용
    fun regexDetail(userDetail: String?): String? {
        val detail = "^[\\s\\S]{1,5000}\$"
        if (!Pattern.matches(detail, userDetail)) throw RegexException(detail, "글자수는 1~5000 사이로 작성해주세요.") else return userDetail
    }

    // 댓글 내용
    fun regexCommentDetail(commentDetail: String): String {
        val detail = "^[\\s\\S]{1,1000}\$"
        if (!Pattern.matches(detail, commentDetail)) throw RegexException(detail, "글자수는 1~1000 사이로 작성해주세요.") else return commentDetail
    }
}