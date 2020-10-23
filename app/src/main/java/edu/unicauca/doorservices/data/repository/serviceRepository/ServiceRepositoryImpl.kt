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
        // 1. an arraylist to store data
        val services = ArrayList<Service>()
        // 2. query firestore database
        db.collection("services")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    Log.d("Getting all services", "${document.data}")
                    // new Service constructor
                    // add new service to services
                }
            }
            .addOnFailureListener {exception ->
                Log.d("error", exception.toString())

            }
        // 3. get all services to the arraylist
        // return arrayList
        return services
    }

    override fun getAllServicesByCategoryId(id: String): ArrayList<Service> {
        TODO("Not yet implemented")
    }
}