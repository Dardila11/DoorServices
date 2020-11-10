package edu.unicauca.doorservices.data.repository.userRepository

import edu.unicauca.doorservices.data.model.UserProfileData

interface UserRepository {

    fun createUser(name: String, email: String)
    suspend fun createProfileData( userProfileData: UserProfileData, userId: String )
    fun getUserById( id: String ) : UserProfileData
    suspend fun getProfileDataById( userId: String) : UserProfileData
}