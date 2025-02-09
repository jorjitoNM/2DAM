package com.example.examenjorgenovillo.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.apptareas.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        with(binding) {
            val navHost =
                supportFragmentManager.findFragmentById(com.example.apptareas.R.id.fragmentContainerView) as androidx.navigation.fragment.NavHostFragment
            navController = navHost.navController
            binding.bottomNavigationView.setupWithNavController(navController)
            setSupportActionBar(topAppBar)
            topAppBar.setNavigationOnClickListener {
                navController.navigateUp()
            }
            setupActionBarWithNavController(navController)
        }
    }

}