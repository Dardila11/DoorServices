package edu.unicauca.doorservices.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.UserProfileChangeRequest
import com.squareup.picasso.Picasso
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.MyRequest
import edu.unicauca.doorservices.data.model.Service
import edu.unicauca.doorservices.data.model.UserProfileData
import edu.unicauca.doorservices.data.repository.authRepository.AuthRepositoryImpl
import edu.unicauca.doorservices.data.repository.serviceRepository.ServiceRepositoryImpl
import edu.unicauca.doorservices.data.repository.userRepository.UserRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_request_service.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
import kotlin.coroutines.CoroutineContext


class RequestServiceFragment : Fragment(), CoroutineScope {

    private var serviceId: String = ""
    private lateinit var job: Job
    private lateinit var service: Service
    private lateinit var userProfileData: UserProfileData
    private var request = MyRequest()
    private  var email : String = ""

    private var serviceRepositoryImpl = ServiceRepositoryImpl()
    private var userRepositoryImpl = UserRepositoryImpl()
    private var authRepositoryImpl = AuthRepositoryImpl()

    private lateinit var  paymentMethod : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            serviceId = it.getString("servId").toString()
        }
    }

    override fun onResume() {
        val toolbar: Toolbar? = activity?.findViewById(R.id.toolBar)
        toolbar?.title = "Solicitud"
        toolbar?.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        super.onResume()
    }

    fun onRadioButtonClicked(view: View) {
        if(view is RadioButton) {
            val checked = view.isChecked
            when(view.id) {
                R.id.daviplata ->
                    if(checked) {
                        paymentMethod = "kT9xf1KEXCycoeAgBU9U"
                        Toast.makeText(activity, paymentMethod, Toast.LENGTH_SHORT).show()
                    }
                R.id.cash ->
                    if(checked) {
                        paymentMethod = "ANU2lmLbNoxQajdRAybx"
                        Toast.makeText(activity, paymentMethod, Toast.LENGTH_SHORT).show()
                    }
                R.id.nequi ->
                    if(checked) {
                        paymentMethod = "qB1X6fh53GBEZytpDKGr"
                        Toast.makeText(activity, paymentMethod, Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_request_service, container, false)

        job = Job()

        lifecycleScope.launch {
            service = serviceRepositoryImpl.getServiceById(serviceId)
            userProfileData = userRepositoryImpl.getProfileDataById(authRepositoryImpl.getUser()?.uid.toString())
            serv_title.text = service.title
            serv_description.text = service.description
            user_address.text = userProfileData.address
            serv_price.text = toCurrencyFormat(service.price)

            Picasso.get()
                .load(service.image)
                .placeholder(R.drawable.cat_placeholder)
                .into(serv_image)
            progressBar.visibility = View.GONE


            btn_send_request.setOnClickListener {
                lifecycleScope.launch {

                    request.docServiceId = "1"
                    request.service_price = "10000"
                    request.docUserId = "2"
                    serviceRepositoryImpl.requestService(request)
                    Toast.makeText(activity, "Solicitud enviada con exito", Toast.LENGTH_SHORT).show()

                    /*Snackbar.make(it, "Solicitud enviada con exito", Snackbar.LENGTH_SHORT)
                        .setAnchorView(bottom_nav)
                        .setAction("Action", null)
                        .show()*/
                }
            }

        }
        return rootView
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
        @JvmStatic
        fun newInstance(serviceId: String) =
            RequestServiceFragment().apply {
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