package edu.unicauca.doorservices.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Category
import edu.unicauca.doorservices.data.model.Service
import edu.unicauca.doorservices.data.repository.categoryRepository.CategoryRepositoryImpl
import edu.unicauca.doorservices.data.repository.serviceRepository.ServiceRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ServicesByCategoryFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job
    private lateinit var recyclerView: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var servicesList : ArrayList<Service>

    private var serviceRepositoryImpl = ServiceRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_services_by_category, container, false)
        job = Job()

        lifecycleScope.launch {
            servicesList = ArrayList()
            //servicesList = serviceRepositoryImpl.getServiceById(1)


        }
        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ServicesByCategoryFragment()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}