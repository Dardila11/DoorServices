package edu.unicauca.doorservices.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Category
import kotlinx.android.synthetic.main.cardview_category.view.*

class CategoriesAdapter(private val categoriesList: ArrayList<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val catTitle: TextView
        // TODO add category image
        init {
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener {
                Log.d(TAG, "Element $adapterPosition clicked")
            }
            catTitle = v.findViewById(R.id.cat_title)
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


    }

    override fun getItemCount() = categoriesList.size

    companion object {
        private const val TAG = "CustomAdapter"
    }


}