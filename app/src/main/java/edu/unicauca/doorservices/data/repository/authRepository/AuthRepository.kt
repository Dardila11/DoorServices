package edu.unicauca.doorservices.data.repository.authRepository

import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun signInWithPhoneNumber(phoneNumber: String) : AuthResult
    suspend fun signInWithEmailAndPassword(email: String, password: String) : AuthResult?
    //suspend fun isSignedIn( user: FirebaseUser ) : Boolean
    //suspend fun signOut()
    suspend fun getUser()


}