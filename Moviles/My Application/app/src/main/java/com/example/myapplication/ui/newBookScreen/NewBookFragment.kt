package com.example.myapplication.ui.newBookScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.NewBookBinding
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.AddBook
import com.example.myapplication.domain.usecases.GetID
import com.example.myapplication.ui.common.StringProvider
import com.example.myapplication.ui.common.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewBookFragment : Fragment() {

    private var _binding: NewBookBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewBookViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        events()
        observarState()
    }

    private fun events() {
        with(binding) {
            add.setOnClickListener() {
                viewModel.handleEvent(
                    NewBookEvents.AddBook(
                        Book(
                            -1,
                            bookName.text.toString(),
                            bookAuthor.text.toString(),
                            ratingBar.rating,
                            releaseDate.text.toString(),
                        )
                    )
                )
            }
            cancel.setOnClickListener() { viewModel.handleEvent(NewBookEvents.Cancel) }
        }
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->

            state.event?.let { event ->
                if (event is UiEvent.PopBackStack) {
                    findNavController().navigateUp()
                } else if (event is UiEvent.ShowSnackbar) {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                }
                viewModel.handleEvent(NewBookEvents.EventoMostrado)
            }

            if (state.event == null) {
                binding.bookName.setText(state.book.name)
                binding.bookAuthor.setText(state.book.author)
                binding.releaseDate.setText(state.book.releaseDate.toString())
                binding.ratingBar.rating = state.book.score
            }
        }
    }
}