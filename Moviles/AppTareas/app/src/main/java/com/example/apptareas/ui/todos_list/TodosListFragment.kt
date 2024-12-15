package com.example.apptareas.ui.todos_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptareas.R
import com.example.apptareas.databinding.TodoListFragmentBinding
import com.example.apptareas.ui.common.MarginItemDecoration
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.utilities.Constantes
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodosListFragment : Fragment(),MenuProvider {

    private val viewModel: TodosListViewModel by viewModels()
    private var _binding: TodoListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TodoAdapter
    private var userId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userId = arguments?.getInt(Constantes.USER_ID) ?: 1
        _binding = TodoListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        observarState()
        configAppBar()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(TodosListEvents.GetTodos)
    }

    private fun observarState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.filtered)
                        adapter.submitList(state.filteredTodos)
                    else
                        adapter.submitList(state.todos)
                    state.appEvent?.let { appEvent ->
                        if (appEvent is UiEvent.ShowSnackbar) {
                            Snackbar.make(binding.root, appEvent.message, Snackbar.LENGTH_SHORT)
                                .show()
                        }
                        viewModel.handleEvent(TodosListEvents.EventDone)
                    }
                }
            }
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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_appbar_search, menu)
        val actionSearch = menu.findItem(R.id.search).actionView as SearchView

        actionSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let {
                    viewModel.handleEvent(TodosListEvents.FilterTodos(p0))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.handleEvent(TodosListEvents.FilterTodos(newText))
                }
                return false
            }
        })
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

    private fun configAppBar() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}