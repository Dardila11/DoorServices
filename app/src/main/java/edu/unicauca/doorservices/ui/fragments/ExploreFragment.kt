package edu.unicauca.doorservices.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Category
import edu.unicauca.doorservices.data.repository.categoryRepository.CategoryRepositoryImpl
import edu.unicauca.doorservices.ui.adapters.CategoriesAdapter
import edu.unicauca.doorservices.ui.adapters.ExploreCatAdapter
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ExploreFragment : Fragment(), CoroutineScope {

    private lateinit var recyclerView: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var categoriesList : ArrayList<Category>
    private lateinit var job: Job

    private var categoryRepositoryImpl = CategoryRepositoryImpl()

    override fun onResume() {
        val toolbar: Toolbar? = activity?.findViewById(R.id.toolBar)
        toolbar?.title = "Explorar"
        toolbar?.navigationIcon = null
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_explore, container, false)
        job = Job()

        lifecycleScope.launch {
            categoriesList = ArrayList()
            categoriesList = categoryRepositoryImpl.getAllCategories()

            recyclerView = rootView.findViewById(R.id.recycler_categories)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = ExploreCatAdapter(categoriesList)
        }
        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

        companion object {
            @JvmStatic
            fun newInstance() = ExploreFragment()
        }


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}