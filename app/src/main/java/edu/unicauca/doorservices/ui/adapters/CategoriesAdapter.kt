package edu.unicauca.doorservices.ui.adapters

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Category
import edu.unicauca.doorservices.ui.MainActivity
import edu.unicauca.doorservices.ui.fragments.ServicesByCategoryFragment

class CategoriesAdapter(private val categoriesList: ArrayList<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val catTitle: TextView
        val catDescription: TextView
        // TODO add category image
        init {
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener {
                Log.d(TAG, "Element $adapterPosition clicked")
            }
            catTitle = v.findViewById(R.id.cat_title)
            catDescription = v.findViewById(R.id.cat_description)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.ViewHolder {
        // Create a new view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_category, parent, false)
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        Log.d(TAG, "Element $position set.")
        holder.catTitle.text = categoriesList[position].categoryName
        holder.catDescription.text = categoriesList[position].categoryDescription

        holder.itemView.setOnClickListener {
            print(categoriesList[position].categoryId)
            val ctx = holder.itemView.context
            //Toast.makeText(ctx, categoriesList[position].categoryId, Toast.LENGTH_SHORT).show()
            // TODO put categoryId inside fragment parameters
            val docCategoryId = categoriesList[position].docCategoryId
            openFragment(ctx, ServicesByCategoryFragment.newInstance(docCategoryId))

        }
    }

    override fun getItemCount() = categoriesList.size

    companion object {
        private const val TAG = "CustomAdapter"
    }

    private fun openFragment(context: Context, fragment: Fragment){
        val myActivityContext = context as MainActivity
        val transaction = myActivityContext.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}