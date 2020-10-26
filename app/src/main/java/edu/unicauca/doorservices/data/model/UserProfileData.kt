package edu.unicauca.doorservices.data.model

class UserProfileData {
    var firstName: String = ""
    var lastName: String = ""
    var legalId: String = ""
    var email: String = ""
    var phone: String = ""
    // this should be a list of payment methods id
    var paymentMethods: ArrayList<String> = ArrayList()
}