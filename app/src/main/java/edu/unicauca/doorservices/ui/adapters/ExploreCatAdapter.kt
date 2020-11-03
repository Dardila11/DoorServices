package edu.unicauca.doorservices.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Category
import edu.unicauca.doorservices.ui.MainActivity
import edu.unicauca.doorservices.ui.fragments.ServicesByCategoryFragment

class ExploreCatAdapter(private val categoriesList: ArrayList<Category>) : RecyclerView.Adapter<ExploreCatAdapter.ViewHolder>() {


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val catTitle: TextView
        val catImage: ImageView
        // TODO add category image
        init {
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener {
                Log.d(TAG, "Element $adapterPosition clicked")
            }
            catTitle = v.findViewById(R.id.cat_title)
            catImage = v.findViewById(R.id.cat_image)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_trending, parent, false)
        return ViewHolder(v)
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

    fun setImage( title: String ): Int {
        var drawable = R.drawable.plomeria
        when(title){
            "plomeria" -> {
                drawable = R.drawable.plomeria
            }
            "Mecanico" -> {
                drawable = R.drawable.mecanico
            }
        }
        return drawable
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.catTitle.text = categoriesList[position].categoryName
        Picasso.get()
            .load(categoriesList[position].categoryImage)
            .placeholder(R.drawable.cat_placeholder)
            .into(holder.catImage)

        holder.itemView.setOnClickListener {
            print(categoriesList[position].categoryId)
            val ctx = holder.itemView.context
            val docCategoryId = categoriesList[position].docCategoryId
            openFragment(ctx, ServicesByCategoryFragment.newInstance(docCategoryId))
        }
    }
}