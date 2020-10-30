package edu.unicauca.doorservices.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Category
import edu.unicauca.doorservices.data.model.Service
import edu.unicauca.doorservices.data.repository.categoryRepository.CategoryRepositoryImpl
import edu.unicauca.doorservices.data.repository.serviceRepository.ServiceRepositoryImpl
import edu.unicauca.doorservices.ui.adapters.ServicesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ServicesByCategoryFragment : Fragment(), CoroutineScope {

    private var categoryId: String = ""

    private lateinit var job: Job
    private lateinit var recyclerView: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var servicesList : ArrayList<Service>

    private var serviceRepositoryImpl = ServiceRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryId = it.getString("categoryId").toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_services_by_category, container, false)
        job = Job()

        lifecycleScope.launch {
            servicesList = ArrayList()
            servicesList = serviceRepositoryImpl.getAllServicesByCategoryId(categoryId)
            if(servicesList.isEmpty()) {
                Toast.makeText(activity, "empty", Toast.LENGTH_SHORT).show()
            }
            recyclerView = rootView.findViewById(R.id.recycler_services)
            layoutManager = LinearLayoutManager(activity)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = ServicesAdapter(servicesList)
        }
        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    companion object {
        @JvmStatic
        fun newInstance(catId: String) = ServicesByCategoryFragment().apply {
            arguments = Bundle().apply {
                putString("categoryId", catId)
            }
        }
    }


}