package edu.unicauca.doorservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav : BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNav.selectedItemId = R.id.menu_search
        bottomNav.setOnNavigationItemSelectedListener { item ->
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