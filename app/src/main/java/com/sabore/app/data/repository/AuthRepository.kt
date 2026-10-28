package com.sabore.app.data.repository

import com.sabore.app.data.local.dao.UserDao
import com.sabore.app.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun observeCurrentUser(): Flow<UserEntity?> = flow {
        var localUser = userDao.getUserById("user_1")
        if (localUser == null) {
            localUser = UserEntity(
                id = "user_1",
                email = "usuario@exemplo.com",
                displayName = "Usuário Saborê",
                avatarUrl = null,
                isPro = false
            )
            userDao.insertUser(localUser)
        }
        emit(localUser)
        userDao.observeUser("user_1").collect { emit(it) }
    }

    suspend fun signInWithGoogle(idToken: String): Result<UserEntity> {
        val user = UserEntity(
            id = "user_1",
            email = "usuario@exemplo.com",
            displayName = "Usuário Saborê",
            avatarUrl = null,
            isPro = false
        )
        userDao.insertUser(user)
        return Result.success(user)
    }

    suspend fun signOut() {}
}
