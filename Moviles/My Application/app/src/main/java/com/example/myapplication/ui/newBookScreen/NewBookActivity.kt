package com.example.myapplication.ui.newBookScreen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.NewBookBinding
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.AddBook
import com.example.myapplication.domain.usecases.GetID
import com.example.myapplication.ui.common.StringProvider
import com.example.myapplication.ui.common.UiEvent

class NewBookActivity  : AppCompatActivity(){

    private lateinit var binding: NewBookBinding

    private val viewModel: NewBookViewModel by viewModels {
        NewBookViewModelFactory(
            AddBook(),
            GetID(),
            StringProvider.instance(this),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = NewBookBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.newBook)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        events()
        observarState()
    }

    private fun events() {
        with(binding) {
            add.setOnClickListener() { viewModel.addBook(Book(viewModel.getId(),bookName.text.toString(),
                bookAuthor.text.toString(),ratingBar.rating,releaseDate.text.toString())) }
            cancel.setOnClickListener() { viewModel.cancel() }
        }
    }

    private fun observarState() {
        viewModel.uiState.observe(this@NewBookActivity) { state ->

            state.mensaje?.let { error ->
                Toast.makeText(this@NewBookActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }

            if (state.mensaje == null) {
                binding.bookName.setText(state.book.name)
                binding.bookAuthor.setText(state.book.author)
                binding.releaseDate.setText(state.book.releaseDate.toString())
                binding.ratingBar.rating = state.book.score
            }

            state.event?.let { event ->
                if (event is UiEvent.PopBackStack) {
                    this@NewBookActivity.finish()
                } else if (event is UiEvent.ShowSnackbar) {
                    Toast.makeText(this@NewBookActivity, event.message, Toast.LENGTH_SHORT).show()
                }
                viewModel.eventoMostrado()
            }

            if (state.event == null)
                binding.bookName.setText(state.book.name)
        }
    }
}