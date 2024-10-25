package com.example.myapplication.ui.detailsScreen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.BookDetailsBinding
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.DeleteBook
import com.example.myapplication.domain.usecases.GetBook
import com.example.myapplication.domain.usecases.GetBooksSize
import com.example.myapplication.domain.usecases.UpdateBook
import com.example.myapplication.ui.common.StringProvider
import com.example.myapplication.ui.common.UiEvent

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: BookDetailsBinding
    private var id : Int = 0

    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.DetailsMainViewModelFactory(
            StringProvider.instance(this),
            UpdateBook(),
            DeleteBook(),
            GetBook(),
            GetBooksSize(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.book_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bookDetails)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = BookDetailsBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        id = intent.extras?.getInt("id") ?: -1
        viewModel.getBook(id)
        eventos()
        observarViewModel()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@DetailsActivity) { state ->

            if (state.event == null) {
                binding.bookName.setText(state.book.name)
                binding.bookAuthor.setText(state.book.author)
                binding.releaseDate.setText(state.book.releaseDate.toString())
                binding.ratingBar.rating = state.book.score
            }

            state.event?.let { event ->
                if (event is UiEvent.PopBackStack) {
                    this@DetailsActivity.finish()
                } else if (event is UiEvent.ShowSnackbar) {
                    Toast.makeText(this@DetailsActivity, event.message, Toast.LENGTH_SHORT).show()
                }
                viewModel.eventoMostrado()
            }

            if (state.event == null)
                binding.bookName.setText(state.book.name)
        }
    }

    private fun eventos() {

        with(binding) {
            update.setOnClickListener {
                viewModel.updateBook(Book(id,bookName.text.toString(),
                    bookAuthor.text.toString(),ratingBar.rating,releaseDate.text.toString()))
            }
            delete.setOnClickListener {
                viewModel.deleteBook(id)
            }
        }
    }
}