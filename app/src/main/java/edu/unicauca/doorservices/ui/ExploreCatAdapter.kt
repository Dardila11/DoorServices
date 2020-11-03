package edu.unicauca.doorservices.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Category
import edu.unicauca.doorservices.ui.adapters.CategoriesAdapter
import edu.unicauca.doorservices.ui.fragments.ServicesByCategoryFragment

class ExploreCatAdapter(private val categoryList: ArrayList<Category>) : RecyclerView.Adapter<ExploreCatAdapter.ViewHolder>() {

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_trending, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = categoryList.size

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}