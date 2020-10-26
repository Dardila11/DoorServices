package edu.unicauca.doorservices.data.repository.serviceRepository

import edu.unicauca.doorservices.data.model.Service


interface ServiceRepository {

    suspend fun getServiceById(id: String) : Service
    suspend fun getAllServices() : ArrayList<Service>
    suspend fun getAllServicesByCategoryId( id: String ) : ArrayList<Service>

    suspend fun createService( service: Service)
    suspend fun updateService(service: Service)
    suspend fun deleteServiceById( servId: String)


}