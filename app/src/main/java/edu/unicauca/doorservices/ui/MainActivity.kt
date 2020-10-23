package edu.unicauca.doorservices.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.doorservices.R
import edu.unicauca.doorservices.data.repository.categoryRepository.CategoryRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.selectedItemId = R.id.menu_search
        bottom_nav.setOnNavigationItemSelectedListener { item ->

            when(item.itemId) {
                // TODO 1: Change this so it returns fragments
                R.id.menu_categories -> {
                    val menuItemSelected = "Categorias"
                    lbl_title.text = menuItemSelected
                    true
                }
                R.id.menu_search -> {
                    val menuItemSelected = "Buscar"
                    lbl_title.text = menuItemSelected
                    true
                }
                R.id.menu_my_orders -> {
                    val menuItemSelected = "Mis pedidos"
                    lbl_title.text = menuItemSelected
                    true
                }
                else -> false
            }
        }
    }
}