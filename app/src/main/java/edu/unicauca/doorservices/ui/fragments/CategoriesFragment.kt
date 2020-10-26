package edu.unicauca.doorservices.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Category
import edu.unicauca.doorservices.data.repository.categoryRepository.CategoryRepositoryImpl
import edu.unicauca.doorservices.ui.adapters.CategoriesAdapter
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CategoriesFragment : Fragment(), CoroutineScope {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var categoriesList : ArrayList<Category>
    private lateinit var job:Job

    private var categoryRepositoryImpl = CategoryRepositoryImpl()

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_categories, container, false)
        job = Job()

        lifecycleScope.launch {
            categoriesList = ArrayList()
            categoriesList = categoryRepositoryImpl.getAllCategories()

            recyclerView = rootView.findViewById(R.id.recycler_categories)
            layoutManager = LinearLayoutManager(activity)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = CategoriesAdapter(categoriesList)
        }

        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            CategoriesFragment()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


}