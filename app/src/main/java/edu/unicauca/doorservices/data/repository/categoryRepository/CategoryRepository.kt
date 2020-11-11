package edu.unicauca.doorservices.data.repository.categoryRepository

import edu.unicauca.doorservices.data.model.Category

interface CategoryRepository {

    fun getCategoryById(id: String)
    suspend fun  getAllCategories(): ArrayList<Category>
    suspend fun  getCategoryIdByName(catName: String) : String


}