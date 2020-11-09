package edu.unicauca.doorservices.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseApp
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.repository.serviceRepository.ServiceRepositoryImpl
import edu.unicauca.doorservices.ui.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val serviceRepositoryImpl = ServiceRepositoryImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolBar)

        toolbar.setOnMenuItemClickListener {item ->
            when(item.itemId) {
                R.id.menu_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_publish -> {
                    //Toast.makeText(this, "Opening search", Toast.LENGTH_SHORT).show()
                    val fragment = PublishServiceFragment.newInstance("1", "2")
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }

        bottom_nav.setOnNavigationItemSelectedListener { item ->

            when(item.itemId) {
                R.id.menu_categories -> {
                    val fragment = CategoriesFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.menu_search -> {
                    val fragment = ExploreFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.menu_my_orders -> {
                    //val fragment = MyRequestsFragment.newInstance("1", "1")
                    val fragment = PublishServiceFragment.newInstance("1", "2")
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        bottom_nav.selectedItemId = R.id.menu_search
    }

    fun getToolbar(){
        this.toolBar
    }

    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}