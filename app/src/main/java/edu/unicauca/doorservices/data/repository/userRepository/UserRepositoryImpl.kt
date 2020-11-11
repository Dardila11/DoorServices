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


    override suspend fun hasProfileData(id: String): Boolean {
        var hasProfile=true
        val documents=db.collection("users")
            .whereEqualTo("userId",id)
            .get().await()
        if(documents.isEmpty){
            hasProfile=false

        }
        return hasProfile
    }

    override suspend fun getUserById(id: String): UserProfileData {
        val documents=db.collection("users")
            .whereEqualTo("userId",id)
            .get().await()
        val userProfileData=UserProfileData()
        for(document in documents){
            val documentData=document.data
            userProfileData.firstName=documentData["firstname"] as String
            userProfileData.lastName=documentData["lastname"] as String
            userProfileData.address=documentData["address"] as String
            userProfileData.legalId=documentData["legalId"] as String
            userProfileData.neighborhood=documentData["neighborhood"] as String
            userProfileData.phone=documentData["phone"] as String
        }
        return userProfileData
    }

    override suspend fun getProfileDataById(userId: String): UserProfileData {
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
