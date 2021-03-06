package edu.unicauca.doorservices.data.repository.categoryRepository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.doorservices.data.model.Category
import kotlinx.coroutines.tasks.await

class CategoryRepositoryImpl: CategoryRepository {

     private val db = Firebase.firestore

    override fun getCategoryById(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCategories(): ArrayList<Category> {
        // TODO handle exceptions
        val categories = ArrayList<Category>()
        val documents = db.collection("categories").get().await()
        for (document in documents) {
            Log.d("document id", document.id)
            val category = Category(document.id, document.data["catId"] as String, document.data["name"] as String, document.data["description"] as String, document.data["image"] as String)
            categories.add(category)
        }
        return categories
    }

    override suspend fun getCategoryIdByName(catName: String): String {
        var categoryId =""
        val documents=db.collection("categories")
            .whereEqualTo("name",catName)
            .get().await()
        for(document in documents){
            categoryId=document.id
        }
        return categoryId
    }
}