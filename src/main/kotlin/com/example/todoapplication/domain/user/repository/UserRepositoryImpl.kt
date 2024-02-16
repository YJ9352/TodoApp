package com.example.todoapplication.domain.user.repository

import com.example.todoapplication.domain.user.model.QUserEntity
import com.example.todoapplication.domain.user.model.UserEntity
import com.example.todoapplication.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl: QueryDslSupport(), CustomUserRepository {

    private val user = QUserEntity.userEntity

    override fun searchUserListByName(userName: String): List<UserEntity> {
        return queryFactory.selectFrom(user)
            .where(user.userName.containsIgnoreCase(userName))
            .fetch()
    }
}