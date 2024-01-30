package com.example.todoapplication.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val userId: Long,
    val userEmail: String,
    val authorities: Collection<GrantedAuthority>
) {
    constructor(userId: Long, userEmail: String, role: Set<String>): this (
        userId,
        userEmail,
        role.map { SimpleGrantedAuthority("ROLE_$it") }
    )


}