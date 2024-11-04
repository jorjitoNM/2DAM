package com.example.myapplication.ui.detailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.BookDetailsBinding
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.DeleteBook
import com.example.myapplication.domain.usecases.GetBook
import com.example.myapplication.domain.usecases.UpdateBook
import com.example.myapplication.ui.common.StringProvider
import com.example.myapplication.ui.common.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: BookDetailsBinding? = null
    private val binding get() = _binding!!
    private var id : Int = -1

    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.DetailsMainViewModelFactory(
            StringProvider(requireContext()),
            UpdateBook(),
            DeleteBook(),
            GetBook(),
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
        val args : DetailsFragmentArgs by navArgs()
        super.onViewCreated(view, savedInstanceState)
        id = args.bookId.toInt()
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
                    findNavController().navigateUp()
                } else if (event is UiEvent.ShowSnackbar) {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
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