package edu.unicauca.doorservices.ui.adapters

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Service
import edu.unicauca.doorservices.ui.MainActivity
import edu.unicauca.doorservices.ui.fragments.ServiceDetailFragment
import edu.unicauca.doorservices.ui.fragments.ServicesByCategoryFragment
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ServicesAdapter(private val servicesList: ArrayList<Service>) : RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val servTitle: TextView
        val servPrice: TextView

        init {
            v.setOnClickListener {
                Log.d(TAG, "Element $adapterPosition clicked")

            }

            servTitle = v.findViewById(R.id.serv_title)
            servPrice = v.findViewById(R.id.serv_price)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_service, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ServicesAdapter.ViewHolder, position: Int) {
        Log.d(ServicesAdapter.TAG, "Element $position set.")

        holder.servTitle.text = servicesList[position].title
        holder.servPrice.text = toCurrencyFormat(servicesList[position].price)


        holder.itemView.setOnClickListener {
            print(servicesList[position].categoryId)
            val ctx = holder.itemView.context
            //Toast.makeText(ctx, categoriesList[position].categoryId, Toast.LENGTH_SHORT).show()
            // TODO put serviceId inside fragment parameters
            // TODO create new Service fragment
            val docServiceId = servicesList[position].docServiceId
            openFragment(ctx, ServiceDetailFragment.newInstance( docServiceId ))

        }
    }

    private fun toCurrencyFormat(number: String) : String {
        val price = number.toInt()
        val formattedNumber = NumberFormat.getCurrencyInstance()
        formattedNumber.maximumFractionDigits = 0
        formattedNumber.currency = Currency.getInstance("COP")
        val fNumber = formattedNumber.format(price)
        return fNumber.replace("COP", "$")

    }

    override fun getItemCount() = servicesList.size

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