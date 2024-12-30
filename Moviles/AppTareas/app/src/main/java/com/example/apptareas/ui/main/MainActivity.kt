package com.example.apptareas.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.example.apptareas.utilities.Constantes
import com.example.primerxmlmvvm.R
import com.example.primerxmlmvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var userId : Int = 1
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = intent.extras?.getInt(Constantes.USER_ID) ?: 1
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        with(binding) {
            val navHost =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHost.navController
            binding.bottomNavigationView.setupWithNavController(navController)
            setSupportActionBar(backArrow)
            backArrow.setNavigationOnClickListener {
                navController.navigateUp()
            }
            setupActionBarWithNavController(navController)
        }
    }
}