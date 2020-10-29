package edu.unicauca.doorservices.data.repository.serviceRepository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.doorservices.data.model.Service
import kotlinx.coroutines.tasks.await

class ServiceRepositoryImpl : ServiceRepository {


    private val db = Firebase.firestore

    override suspend fun getServiceById(id: String): Service {
        val result = db.collection("Services").document(id).get().await()

        TODO("new service")

    }

    override suspend fun getAllServices(): ArrayList<Service> {
        val servicesList = ArrayList<Service>()
        val documents = db.collection("Services").get().await()
        for (document in documents){
            val service = Service().fromMap(document.data)
            servicesList.add(service)
        }
        return servicesList
    }

    override suspend fun getAllServicesByCategoryId(id: String): ArrayList<Service> {
        val servicesByCategoryList = ArrayList<Service>()
        val documents = db.collection("Services")
            .whereEqualTo("category_id", id).get().await()

        for(document in documents) {
            val service = Service().fromMap(document.data)
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
}