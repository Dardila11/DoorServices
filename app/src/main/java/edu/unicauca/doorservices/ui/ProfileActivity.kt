package edu.unicauca.doorservices.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.model.UserProfileData
import edu.unicauca.doorservices.data.repository.authRepository.AuthRepositoryImpl
import edu.unicauca.doorservices.data.repository.userRepository.UserRepositoryImpl
import kotlinx.android.synthetic.main.content_profile.*
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


        btn_userInfo.setOnClickListener{
            lifecycleScope.launch {
                userProfileData = UserProfileData(
                    txt_firstname.text.toString(),
                    txt_lastname.text.toString(),
                    txt_id.text.toString(),
                    txt_address.text.toString(),
                    txt_hood.text.toString(),
                    txt_phone.text.toString())


                Snackbar.make(it, authRepositoryImpl.getUser()?.email.toString(), Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()

                userRepositoryImpl.createProfileData(userProfileData, authRepositoryImpl.getUser()?.uid.toString())
            }
        }



        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}