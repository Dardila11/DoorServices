package edu.unicauca.doorservices.data.repository.userRepository

import edu.unicauca.doorservices.data.model.UserProfileData

interface  UserRepository {

    fun createUser(name: String, email: String)
    suspend fun createProfileData( userProfileData: UserProfileData, userId: String )
    suspend fun getUserById( id: String ) : UserProfileData
    suspend fun getProfileDataById(userId: String):UserProfileData
    suspend fun hasProfileData(id: String):Boolean
}