package com.example.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.data.Repository
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.usecases.AddBook
import com.example.myapplication.domain.usecases.DeleteBook
import com.example.myapplication.domain.usecases.GetBooks
import com.example.myapplication.domain.usecases.UpdateBook

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val  repository : Repository = Repository.getInstance()


    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            AddBook(repository),
            DeleteBook(repository),
            GetBooks(repository),
            UpdateBook(repository),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        binding.submit.setOnClickListener {
            Toast.makeText(this,binding.nameInput.text,Toast.LENGTH_LONG).show()
        }
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->

            state.error?.let { error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }


            if (state.error == null)
                binding.editTextTextPersonName.setText(state.persona?.nombre)
        }
    }
}