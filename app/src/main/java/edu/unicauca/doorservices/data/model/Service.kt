package edu.unicauca.doorservices.data.model


class Service {

     private var serviceId: String = ""
     private var categoryId: String = ""
     private var userId: String = ""
     private var title: String = ""
     private var description: String = ""
     private var price: String = ""

    constructor()

    constructor(servId: String, catId: String, userId: String, servTitle: String,servDescription: String,servPrice: String) {
        this.serviceId = servId
        this.categoryId = catId
        this.userId = userId
        this.title = servTitle
        this.description = servDescription
        this.price = servPrice

    }

    /**
      * function to retrieve all fields from firestore document
      * @param map: Map<String,String> -> document.data
      * @return new Service
      * TODO: Null check
     */
    fun fromMap(map: MutableMap<String, Any>) : Service {
        val servTitle         = map["title"] as String
        val servDescription   = map["description"] as String
        val servPrice         = map["price"] as String
        val servId            = map["serv_id"] as String
        val userId            = map["user_id"] as String
        val categoryId        = map["category_id"] as String

        return Service(servId,categoryId,userId,servTitle,servDescription,servPrice)
    }

    /**
     * function to post new service to firestore collection
     * @param service
     * @return map of key and value data
     */
    fun toMap( service: Service) : MutableMap<String, Any>? {
        TODO("Not yet implemented")
    }






 }
