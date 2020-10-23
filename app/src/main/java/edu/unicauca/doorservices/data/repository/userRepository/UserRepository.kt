package edu.unicauca.doorservices.data.repository.userRepository

import edu.unicauca.doorservices.data.model.UserProfileData

interface UserRepository {

    fun createUser(name: String, email: String)
    fun createProfileData( userProfileData: UserProfileData )
    fun getUserById( id: String ) : UserProfileData
}