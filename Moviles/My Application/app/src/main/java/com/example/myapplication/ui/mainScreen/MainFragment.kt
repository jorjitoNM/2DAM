package com.example.myapplication.ui.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.BookListFragmentBinding
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.GetBooks
import com.example.myapplication.ui.common.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: BookListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: BookAdapter
    private val viewModel: MainViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        events()
        configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(MainEvents.GetBooks)
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.books)
        }
    }

    private fun configureRecyclerView() {

        adapter = BookAdapter(
            actions = object : BookAdapter.BookActions {
                override fun onItemClick(book: Book) {
                    navigateToDetail((book.id))
                }
            })
        with (binding) {
            bookList.layoutManager = LinearLayoutManager(requireContext())

            bookList.adapter = adapter

            bookList.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.margin
                    )
                )
            )
        }
    }


    private fun navigateToDetail(id: Int) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(id));
    }

    private fun events() {
        with(binding) {
            add.setOnClickListener {
                navigateToNewBook()
            }
        }
    }

    private fun navigateToNewBook() {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToNewBookFragment());
    }
}