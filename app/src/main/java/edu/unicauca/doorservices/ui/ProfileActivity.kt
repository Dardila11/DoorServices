package edu.unicauca.doorservices.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.TooltipCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.UserProfileData
import edu.unicauca.doorservices.data.repository.authRepository.AuthRepositoryImpl
import edu.unicauca.doorservices.data.repository.userRepository.UserRepositoryImpl
import edu.unicauca.doorservices.ui.fragments.PublishServiceFragment
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.content_profile.*
import kotlinx.android.synthetic.main.fragment_auth.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileActivity : AppCompatActivity(), CoroutineScope {

    private var userRepositoryImpl = UserRepositoryImpl()
    private var authRepositoryImpl = AuthRepositoryImpl()
    private lateinit var userProfileData: UserProfileData
    private lateinit var job: Job


    override fun onResume() {
        val toolbar: Toolbar? = findViewById(R.id.toolbar)
        toolbar?.title = "Mi Perfil"
        toolbar?.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(findViewById(R.id.toolbar))
        job = Job()

        // if profile data exists
        // put data into text inputs



        btn_userInfo.setOnClickListener{
            lifecycleScope.launch {
                userProfileData = UserProfileData(
                        txt_firstname.text.toString(),
                        txt_lastname.text.toString(),
                        txt_id.text.toString(),
                        txt_address.text.toString(),
                        txt_hood.text.toString(),
                        txt_phone.text.toString())
                }
            lifecycleScope.launch {
                if (userRepositoryImpl.hasProfileData(authRepositoryImpl.getUser()!!.uid)){
                    userProfileData= UserProfileData()
                    userProfileData=userRepositoryImpl.getProfileDataById(authRepositoryImpl.getUser()!!.uid)
                    txt_firstname.setText(userProfileData.firstName)
                    txt_lastname.setText(userProfileData.lastName)
                    txt_id.setText(userProfileData.legalId)
                    txt_address.setText(userProfileData.address)
                    txt_hood.setText(userProfileData.neighborhood)
                    txt_phone.setText(userProfileData.phone)
            }





                Snackbar.make(it, "Datos guardados correctamente", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()

                var result = userRepositoryImpl.createProfileData(userProfileData, authRepositoryImpl.getUser()?.uid.toString())

            }
        }

        TooltipCompat.setTooltipText(fab, "Publicar Servicio")
        fab.setOnClickListener {
            // check if user has profile
            // if it does, open fragment to create a service
            //val fragment = PublishServiceFragment.newInstance("1", "1");
            //openFragment(fragment)
            // else
            // show text "you neeed to create profiledata"
            Snackbar.make(it, "Funcionalidad no integrada", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }
    }

    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}