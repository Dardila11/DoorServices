package edu.unicauca.doorservices.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.repository.authRepository.AuthRepositoryImpl
import kotlinx.android.synthetic.main.fragment_auth.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class AuthFragment : Fragment(), CoroutineScope, View.OnClickListener {


    private lateinit var job: Job
    private var authRepositoryImpl = AuthRepositoryImpl()

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_sign_in -> {
                if( txt_email.text.isEmpty() || txt_password.text.isEmpty() ){
                    Toast.makeText(activity, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show()
                } else {
                    lifecycleScope.launch {
                        authRepositoryImpl.signInWithEmailAndPassword(txt_email.text.toString(), txt_password.text.toString())
                    }
                }

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_sign_in.setOnClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.fragment_auth, container, false)
        job = Job()
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


}