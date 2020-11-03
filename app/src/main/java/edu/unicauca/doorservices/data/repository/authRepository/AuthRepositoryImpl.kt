package edu.unicauca.doorservices.data.repository.authRepository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl : AuthRepository {

    private val auth : FirebaseAuth = Firebase.auth
    private val user = auth.currentUser


    override suspend fun signInWithPhoneNumber(phoneNumber: String) : AuthResult {
        TODO("Not yet implemented")
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult? {

        return try {
            val data = auth.signInWithEmailAndPassword(email, password).await()

            print(data.user?.email)
            data

        }catch (e: Exception) {
            null
        }
    }

    override  fun getUser() : FirebaseUser? {
        return user
    }

    override fun signOut() {
        if(user != null) {
        auth.signOut()
        }
    }
}