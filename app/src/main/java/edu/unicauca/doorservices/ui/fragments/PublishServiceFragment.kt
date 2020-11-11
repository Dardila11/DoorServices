package edu.unicauca.doorservices.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Service
import edu.unicauca.doorservices.data.repository.authRepository.AuthRepositoryImpl
import edu.unicauca.doorservices.data.repository.categoryRepository.CategoryRepositoryImpl
import edu.unicauca.doorservices.data.repository.serviceRepository.ServiceRepositoryImpl
import kotlinx.android.synthetic.main.fragment_publish_service.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

import kotlin.coroutines.CoroutineContext


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PublishServiceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
public  class PublishServiceFragment : Fragment(), CoroutineScope {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var job:Job
    private var serviceRepositoryImpl=ServiceRepositoryImpl()
    private var categoryRepositoryImpl=CategoryRepositoryImpl()
    private var authRepositoryImpl=AuthRepositoryImpl()
    private lateinit var service: Service
    val image ="https://firebasestorage.googleapis.com/v0/b/doorservices-uni.appspot.com/o/plomeria.png?alt=media&token=8765a66a-b279-4a50-924e-1505553cc0c5"


    override fun onResume() {
        super.onResume()
        val toolbar: Toolbar? = activity?.findViewById(R.id.toolBar)
        toolbar?.title = "Publicar"
        toolbar?.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        super.onResume()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onViewCreated(view: View, savedIntanceState:Bundle?){
        super.onViewCreated(view,savedIntanceState)
        val categories= arrayOf("plomeria", "Cuidado personal","Mecanico","Carpinteria","Jardineria")
        val adapter=ArrayAdapter(view.context,R.layout.dropdown_menu_popup_item,categories)
        txt_category.setAdapter(adapter)
            btn_publish.setOnClickListener{
                var catId=""

                lifecycleScope.launch{
                try{

                    catId=categoryRepositoryImpl.getCategoryIdByName(txt_category.text.toString())
                    Snackbar.make(it,catId,Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show()
                }catch(e:Exception){
                    Snackbar.make(it,"Hubo un error",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show()
                }
                try{
                    service=Service("2", catId,authRepositoryImpl.getUser()?.uid.toString(),
                        txt_title.text.toString(),txt_description.text.toString(),txt_price.text.toString(),image)
                    val result=serviceRepositoryImpl.createService(service)
                    Snackbar.make(it,"Datos Guardados correctamente",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show()

                }catch (e:Exception){
                    Snackbar.make(it,"Hubo un error",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show()
                }

            }
        }
    }
    fun onCreatedView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View?{

        val rootView= inflater.inflate(R.layout.fragment_publish_service,container,false)
        job= Job()
        val categories = arrayOf("plomeria", "Cuidado personal","Mecanico", "Carpinteria","Jardineria")
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PublishServiceFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PublishServiceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    override val coroutineContext: CoroutineContext
        get()=Dispatchers.Main + job



}