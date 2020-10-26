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
        val categories = ArrayList<Category>()
        val documents = db.collection("categories").get().await()
        for (document in documents) {
            val category = Category(document.data["catId"] as String, document.data["name"] as String)
            categories.add(category)
        }
        return categories
    }


    /*override suspend fun getAllCategories(): ArrayList<Category> {
        val categories = ArrayList<Category>()
        db.collection("categories")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val category = Category(document.data["catId"] as String, document.data["name"] as String)
                    categories.add(category)
                }
            }
            .addOnFailureListener() { exception ->
                Log.w("Shit, error", "Error getting documents.", exception)
            }
        return categories
    }*/
}