package edu.unicauca.doorservices.data.repository.serviceRepository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.doorservices.data.model.Service

class ServiceRepositoryImpl : ServiceRepository {


    private val db = Firebase.firestore

    override fun getServiceById(id: String) {
        TODO("Not yet implemented")
    }

    override fun getAllServices(): ArrayList<Service> {
        val servicesList = ArrayList<Service>()
        db.collection("Services")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    val service = Service().fromMap(document.data)
                    servicesList.add(service)
                }
            }
            .addOnFailureListener {exception ->
                Log.d("error", exception.toString())

            }
        return servicesList
    }

    override fun getAllServicesByCategoryId(id: String): ArrayList<Service> {
        TODO("Not yet implemented")
    }
}