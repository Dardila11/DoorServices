package edu.unicauca.doorservices.data.model

class UserProfileData {
    var firstName: String = ""
    var lastName: String = ""
    var legalId: String = ""
    var address: String = ""
    var neighborhood: String = ""
    //var email: String = ""
    var phone: String = ""

    constructor()

    constructor(firstName: String, lastName: String, legalId: String, address: String,
                        neighborhood: String, phone: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.legalId = legalId
        this.address = address
        this.neighborhood = neighborhood
        //this.email = email
        this.phone = phone
    }

    /**
     * function to post a user profileData to firestore collection
     * @param userId user firebase id
     * @return map of key and value data
     */
    fun toMap(userId: String) : HashMap<String, String> {
        return hashMapOf(
            "userId" to userId,
            "firstname" to firstName,
            "lastname" to lastName,
            "legalId" to legalId,
            "address" to address,
            "neighborhood" to neighborhood,
            "phone" to phone
        )
    }


}