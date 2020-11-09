package edu.unicauca.doorservices.data.repository.userRepository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.doorservices.data.model.UserProfileData
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl: UserRepository {

    private val db = Firebase.firestore

    override fun createUser(name: String, email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createProfileData(userProfileData: UserProfileData, userId: String) {
        val map = userProfileData.toMap(userId)
        val result = db.collection("users")
            .add(map)
            .await()
        // check result.

    }

    override fun getUserById(id: String): UserProfileData {
        TODO("Not yet implemented")
    }


}