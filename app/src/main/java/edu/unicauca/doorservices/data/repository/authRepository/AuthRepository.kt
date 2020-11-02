package edu.unicauca.doorservices.data.repository.authRepository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signInWithPhoneNumber(phoneNumber: String) : AuthResult
    suspend fun signInWithEmailAndPassword(email: String, password: String) : AuthResult?
    //suspend fun isSignedIn( user: FirebaseUser ) : Boolean
    //suspend fun signOut()
    fun getUser() : FirebaseUser?
    fun signOut()


}