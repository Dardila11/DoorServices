package edu.unicauca.doorservices.data.repository.userRepository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.doorservices.data.model.Service
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

    override suspend fun getProfileDataById(userId: String): UserProfileData {
        val documents = db.collection("users").get().await()
        var userProfileData = UserProfileData()
        for (document in documents) {

            val id = document.data["userId"] as String
            if (userId == id) {
                userProfileData = UserProfileData(
                    document.data["firstname"] as String,
                    document.data["lastname"] as String,
                    document.data["legalId"] as String,
                    document.data["address"] as String,
                    document.data["neighborhood"] as String,
                    document.data["phone"] as String,
                )

            }
        }
        return userProfileData
    }
}
