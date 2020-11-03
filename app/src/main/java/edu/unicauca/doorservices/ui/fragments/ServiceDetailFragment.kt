package edu.unicauca.doorservices.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.Service
import edu.unicauca.doorservices.data.repository.authRepository.AuthRepositoryImpl
import edu.unicauca.doorservices.data.repository.serviceRepository.ServiceRepositoryImpl
import edu.unicauca.doorservices.ui.MainActivity
import kotlinx.android.synthetic.main.cardview_service.serv_title
import kotlinx.android.synthetic.main.fragment_service_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.NumberFormatException
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import kotlin.coroutines.CoroutineContext


class ServiceDetailFragment : Fragment(), CoroutineScope {

    private var serviceId: String = ""
    private lateinit var job: Job
    private lateinit var service: Service
    private  var email : String = ""

    private var serviceRepositoryImpl = ServiceRepositoryImpl()
    private var authRepositoryImpl = AuthRepositoryImpl()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            serviceId = it.getString("servId").toString()
        }
    }

    override fun onResume() {
        val toolbar: Toolbar? = activity?.findViewById(R.id.toolBar)
        toolbar?.title = "Detalles del Servicio"
        toolbar?.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        super.onResume()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_service_detail, container, false)
        job = Job()

        lifecycleScope.launch {
            service = serviceRepositoryImpl.getServiceById(serviceId)
            serv_title.text = service.title
            serv_description.text = service.description
            serv_price.text = toCurrencyFormat(service.price)

            btn_request_service.setOnClickListener {
                val user = authRepositoryImpl.getUser()
                if(user != null) {
                    Toast.makeText(activity, "User signed in ${user.email.toString()}", Toast.LENGTH_SHORT).show()
                    //openFragment(activity, RequestServiceFragment.newInstance("1", "2"))
                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(R.id.main_container, RequestServiceFragment.newInstance("1", "2"))
                    transaction?.addToBackStack(null)
                    transaction?.commit()


                } else {
                    Toast.makeText(activity, "User not signed in", Toast.LENGTH_SHORT).show()
                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(R.id.main_container, AuthFragment.newInstance())
                    transaction?.addToBackStack(null)
                    transaction?.commit()
                }
            }


        }

        return rootView
    }

    private fun openFragment(context: Context, fragment: Fragment){
        val myActivityContext = context as MainActivity
        val transaction = myActivityContext.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun toCurrencyFormat(number: String) : String {
        val price = number.toInt()
        val formattedNumber = NumberFormat.getCurrencyInstance()
        formattedNumber.maximumFractionDigits = 0
        formattedNumber.currency = Currency.getInstance("COP")
        val fNumber = formattedNumber.format(price)
        return fNumber.replace("COP", "$")

    }

    companion object {
        /**
         * @param serviceId id of specific service.
         * @return A new instance of fragment ServiceDetailFragment.
         */
        @JvmStatic
        fun newInstance(serviceId: String) =
            ServiceDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("servId", serviceId)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}