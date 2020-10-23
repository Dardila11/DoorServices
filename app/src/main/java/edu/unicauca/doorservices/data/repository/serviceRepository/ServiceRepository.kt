package edu.unicauca.doorservices.data.repository.serviceRepository

import edu.unicauca.doorservices.data.model.Service


interface ServiceRepository {

    fun getServiceById(id: String)
    fun getAllServices() : List<Service>
    fun getAllServicesByCategoryId( id: String ) : List<Service>

}