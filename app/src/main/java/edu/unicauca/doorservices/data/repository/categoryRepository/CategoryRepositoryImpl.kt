package edu.unicauca.doorservices.data.repository.categoryRepository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.doorservices.data.model.Category

class CategoryRepositoryImpl: CategoryRepository {

     private val db = Firebase.firestore

    override fun getCategoryById(id: String) {
        TODO("Not yet implemented")
    }

    override fun getAllCategories(): ArrayList<Category> {
        val categories = ArrayList<Category>();
        db.collection("categories")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("all categories", "${document.id} => ${document.data}")
                    val categoryName: String =  document.data.getValue("name").toString()
                    Log.d("category name ", categoryName)
                    val cat = Category(document.id,  categoryName)
                    categories.add(cat)

                }
            }
            .addOnFailureListener() { exception ->
                Log.w("Shit, error", "Error getting documents.", exception)
            }

        return categories

    }
}