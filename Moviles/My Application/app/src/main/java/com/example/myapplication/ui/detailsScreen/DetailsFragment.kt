package com.example.myapplication.ui.detailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.DeleteBook
import com.example.myapplication.domain.usecases.GetBook
import com.example.myapplication.domain.usecases.GetBooksSize
import com.example.myapplication.domain.usecases.UpdateBook
import com.example.myapplication.ui.common.StringProvider
import com.example.myapplication.ui.common.UiEvent
import com.example.viewmodel.databinding.BookDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: BookDetailsBinding? = null
    private val binding get() = _binding!!
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = intent.extras?.getInt("id") ?: -1
        viewModel.handleEvent(DetailsEvents.GetBook(id))
        eventos()
        observarViewModel()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->

            if (state.event == null) {
                binding.bookName.setText(state.book.name)
                binding.bookAuthor.setText(state.book.author)
                binding.releaseDate.setText(state.book.releaseDate.toString())
                binding.ratingBar.rating = state.book.score
            }

            state.event?.let { event ->
                if (event is UiEvent.PopBackStack) {
                    this@DetailsFragment.finish()
                } else if (event is UiEvent.ShowSnackbar) {
                    Toast.makeText(this@DetailsFragment, event.message, Toast.LENGTH_SHORT).show()
                }
                viewModel.handleEvent(DetailsEvents.ErrorMostrado)
            }

            if (state.event == null)
                binding.bookName.setText(state.book.name)
        }
    }

    private fun eventos() {

        with(binding) {
            update.setOnClickListener {
                viewModel.handleEvent(DetailsEvents.UpdateBook(Book(id,bookName.text.toString(),
                    bookAuthor.text.toString(),ratingBar.rating,releaseDate.text.toString())))
            }
            delete.setOnClickListener {
                viewModel.handleEvent(DetailsEvents.DeleteBook(id))
            }
        }
    }
}