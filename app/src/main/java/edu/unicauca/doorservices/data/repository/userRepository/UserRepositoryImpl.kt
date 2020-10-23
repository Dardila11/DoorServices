package edu.unicauca.doorservices.data.repository.userRepository

import edu.unicauca.doorservices.data.model.UserProfileData

class UserRepositoryImpl: UserRepository {


    // here we create a new instance of firestore db
    override fun createUser(name: String, email: String) {
        TODO("Not yet implemented")
    }

    override fun createProfileData(userProfileData: UserProfileData) {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: String): UserProfileData {
        TODO("Not yet implemented")
    }


}