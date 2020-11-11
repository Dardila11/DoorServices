package edu.unicauca.doorservices.ui.adapters

import android.widget.Filter
import android.widget.Filterable
import android.content.Context
import android.os.Bundle
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

import kotlinx.android.synthetic.main.cardview_category.view.*
import java.util.*
import kotlin.collections.ArrayList

class CategoriesAdapter(private val categoriesList: ArrayList<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>(),Filterable {
    var categoryFilterList= ArrayList<Category>()
    init{
        categoryFilterList=categoriesList
    }
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val catTitle: TextView
        val catDescription: TextView
        val catImage: ImageView
        // TODO add category image
        init {
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener {
                Log.d(TAG, "Element $adapterPosition clicked")
            }
            catTitle = v.findViewById(R.id.cat_title)
            catDescription = v.findViewById(R.id.cat_description)
            catImage = v.findViewById(R.id.cat_image)
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
        //holder.catImage.setImageResource(setImage(categoriesList[position].categoryImage))
        holder.itemView.cat_title.text= categoryFilterList[position].categoryName

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

    override fun getItemCount() = categoryFilterList.size

    companion object {
        private const val TAG = "CustomAdapter"
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

    private fun openFragment(context: Context, fragment: Fragment){
        val myActivityContext = context as MainActivity
        val transaction = myActivityContext.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun getFilter():Filter{
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch=constraint.toString()
                if(charSearch.isEmpty()){
                    categoryFilterList=categoriesList
                }else{
                    val resultList= ArrayList<Category>()
                    for(category in  categoriesList) {
                        if(category.categoryName.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))){
                            resultList.add(category)

                        }
                    }
                    categoryFilterList=resultList

                }
                val filterResults= FilterResults()
                filterResults.values=categoryFilterList
                return filterResults
            }
            @Suppress("UNCHECKED_CAST")

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                categoryFilterList=results?.values as ArrayList<Category>
                notifyDataSetChanged()
            }
        }
    }


}