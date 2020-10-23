package edu.unicauca.doorservices.data.model

import android.media.Image

class Service(
    var serviceId: String,
    var categoryId: String,
    var userId: String,
    var title: String,
    var description: String,
    var price: Double,
) {



    override fun toString(): String {
        return super.toString()
    }
}

