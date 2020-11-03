package edu.unicauca.doorservices.data.model

class MyRequest {

    var docServiceId: String = ""
    var docUserId: String = ""
    var service_price: String = ""

    constructor()

    constructor(docServiceId: String, docUserId: String, serv_price: String ){
        this.docServiceId = docServiceId
        this.docUserId = docUserId
        this.service_price = serv_price
    }

    /**
     * function to post new service to firestore collection
     * @return map of key and value data
     */
    fun toMap() : HashMap<String, String> {
        return hashMapOf(
            "serv_id" to docServiceId,
            "serv_price" to service_price,
            "user_id" to docUserId,
        )
    }
}