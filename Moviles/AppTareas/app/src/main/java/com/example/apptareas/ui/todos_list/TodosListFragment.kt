package com.example.apptareas.ui.todos_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptareas.R
import com.example.apptareas.databinding.TodoListFragmentBinding
import com.example.apptareas.ui.common.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodosListFragment : Fragment() {

    private val viewModel: TodosListViewModel by viewModels()
    private var _binding: TodoListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TodoAdapter
    private var userId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userId = arguments?.getInt("userId") ?: 1
        _binding = TodoListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(TodosListEvents.GetTodos(userId))
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            if (state.appEvent != null)
                viewModel.handleEvent(TodosListEvents.EventDone)
            adapter.submitList(state.todos)
        }
    }

    private fun configureRecyclerView() {
        adapter = TodoAdapter()
        with(binding) {
            todosList.layoutManager = LinearLayoutManager(requireContext())
            todosList.adapter = adapter
            todosList.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.margin
                    )
                )
            )
        }
    }
}