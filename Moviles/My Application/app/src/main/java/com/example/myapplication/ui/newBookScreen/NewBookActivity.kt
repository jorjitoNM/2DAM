package com.example.myapplication.ui.newBookScreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.NewBookBinding
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.AddBook
import com.example.myapplication.ui.common.StringProvider

class NewBookActivity  : AppCompatActivity(){

    private lateinit var binding: NewBookBinding

    private val viewModel: NewBookViewModel by viewModels {
        NewBookViewModelFactory(
            AddBook(),
            StringProvider.instance(this),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = NewBookBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        events()
        observarState()
    }

    private fun events() {
        with(binding) {
            add.setOnClickListener() { viewModel.addBook(Book()) }
        }
    }

    private fun observarState() {
        TODO("Not yet implemented")
    }
}