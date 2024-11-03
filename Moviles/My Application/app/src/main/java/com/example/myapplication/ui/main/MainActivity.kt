package com.example.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.GetBooks
import com.example.myapplication.ui.common.MarginItemDecoration
import com.example.myapplication.ui.detailsScreen.DetailsFragment
import com.example.myapplication.ui.mainScreen.BookAdapter
import com.example.myapplication.ui.mainScreen.MainViewModel
import com.example.myapplication.ui.mainScreen.MainViewModelFactory
import com.example.myapplication.ui.newBookScreen.NewBookFragment
import com.example.viewmodel.R
import com.example.viewmodel.databinding.BookListFragmentBinding

class MainActivity : AppCompatActivity() {
    private val binding: BookListFragmentBinding by lazy {
        BookListFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        with(binding) {
            setContentView(root)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentContainerView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}