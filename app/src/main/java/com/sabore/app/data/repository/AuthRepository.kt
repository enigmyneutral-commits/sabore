package com.sabore.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sabore.app.data.local.dao.UserDao
import com.sabore.app.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userDao: UserDao
) {
    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    fun observeCurrentUser(): Flow<UserEntity?> = flow {
        val fbUser = firebaseAuth.currentUser
        if (fbUser != null) {
            var localUser = userDao.getUserById(fbUser.uid)
            if (localUser == null) {
                localUser = UserEntity(
                    id = fbUser.uid,
                    email = fbUser.email ?: "",
                    displayName = fbUser.displayName ?: "Cozinheiro(a)",
                    avatarUrl = fbUser.photoUrl?.toString(),
                    isPro = false
                )
                userDao.insertUser(localUser)
            }
            emit(localUser)
            // Emit updates from database
            userDao.observeUser(fbUser.uid).collect { emit(it) }
        } else {
            emit(null)
        }
    }

    suspend fun signInWithFirebaseToken(idToken: String, displayName: String?, email: String?, photoUrl: String?): Result<UserEntity> {
        return try {
            val credential = com.google.firebase.auth.GoogleAuthProvider.getCredential(idToken, null)
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            val fbUser = authResult.user ?: throw Exception("Falha na autenticação com Firebase")

            val userEntity = UserEntity(
                id = fbUser.uid,
                email = email ?: fbUser.email ?: "",
                displayName = displayName ?: fbUser.displayName ?: "Cozinheiro(a)",
                avatarUrl = photoUrl ?: fbUser.photoUrl?.toString(),
                isPro = false
            )
            userDao.insertUser(userEntity)
            Result.success(userEntity)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signOut() {
        firebaseAuth.signOut()
    }
}
