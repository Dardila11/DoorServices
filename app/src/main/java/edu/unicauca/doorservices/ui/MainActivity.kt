package edu.unicauca.doorservices.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseApp
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.repository.serviceRepository.ServiceRepositoryImpl
import edu.unicauca.doorservices.ui.fragments.CategoriesFragment
import edu.unicauca.doorservices.ui.fragments.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val serviceRepositoryImpl = ServiceRepositoryImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContentView(R.layout.activity_main)



        serviceRepositoryImpl.getAllServices()


        bottom_nav.setOnNavigationItemSelectedListener { item ->

            when(item.itemId) {
                R.id.menu_categories -> {
                    val fragment = CategoriesFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.menu_search -> {
                    val fragment = SearchFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.menu_my_orders -> {
                    true
                }
                else -> false
            }
        }
        bottom_nav.selectedItemId = R.id.menu_search
    }

    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}