package edu.unicauca.doorservices.data.repository.serviceRepository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.doorservices.data.model.MyRequest
import edu.unicauca.doorservices.data.model.Service
import kotlinx.coroutines.tasks.await

class ServiceRepositoryImpl : ServiceRepository {


    private val db = Firebase.firestore

    override suspend fun getServiceById(id: String): Service {

        val document = db.collection("Services").document(id).get().await()
        val service = Service()

        val documentData = document.data
        if (documentData != null) {
            if(documentData.isNotEmpty()) {
                service.title = documentData["title"] as String
                service.description = documentData["description"] as String
                service.price = documentData["price"] as String
                service.docServiceId = id
            }

        }

        return service
    }

    override suspend fun getAllServices(): ArrayList<Service> {
        val servicesList = ArrayList<Service>()
        val documents = db.collection("Services").get().await()
        for (document in documents){
            val service = Service().fromMap(document.data)
            service.docServiceId = document.id
            servicesList.add(service)
        }
        return servicesList
    }

    override suspend fun getAllServicesByCategoryId(id: String): ArrayList<Service> {
        val servicesByCategoryList = ArrayList<Service>()
        val documents = db.collection("Services")
            .whereEqualTo("category_id", id)
            .get()
            .await()

        for(document in documents) {
            val service = Service().fromMap(document.data)
            service.docServiceId = document.id
            servicesByCategoryList.add(service)
        }
        return servicesByCategoryList

    }

    override suspend fun createService(service: Service) {
        // 1. Convert Service to map
        val map = service.toMap()
        // 2. Insert query into firestore
        val result = db.collection("Services")
            .add(map)
            .await()
    }

    override suspend fun updateService(service: Service) {
        val map = service.toMap()
        val result = db.collection("Services")
            .document(service.serviceId)
            .update(map as Map<String, Any>)
    }

    override suspend fun deleteServiceById(servId: String) {

        val result = db.collection("Services").document(servId).delete().await()
    }

    override suspend fun requestService(myRequest: MyRequest) {
        val map = myRequest.toMap()

        val result = db.collection("service_requests").add(map).await()
    }
}