package com.example.primeraapp.ui.pantallaPosts

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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.primeraapp.R
import com.example.primeraapp.databinding.FragmentPostBinding
import com.example.primeraapp.domain.modelo.Post
import com.example.primeraapp.ui.common.ConstantesUI
import com.example.primeraapp.ui.common.MarginItemDecoration
import com.example.primeraapp.ui.common.UiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostFragment : Fragment(), MenuProvider {
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PostAdapter
    private val viewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configAppBar()

        activity?.intent?.getIntExtra(ConstantesUI.USERID, -1)?.let { id ->
            if (id != -1) {
                viewModel.handleEvent(PostEvent.GetPost(id))
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configureRecyclerView()
        eventos()
        observarState()

    }

    private fun eventos() {
        with(binding) {
            floatingAddB.setOnClickListener {
                val action = PostFragmentDirections.actionMainFragmentToAddFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun observarState() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state.event?.let { event ->
                        if (event is UiEvent.ShowSnackbar) {
                            val mys =
                                Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT)
                            mys.setAction(ConstantesUI.UNDO) {
                                viewModel.handleEvent(PostEvent.UndoDelete)
                            }
                            mys.show()
                            viewModel.handleEvent(PostEvent.ErrorMostrado)
                        }
                    }
                    adapter.submitList(state.posts)
                    adapter.setFullData(state.posts)
                    binding.progressBar.visibility =
                        if (state.isLoading) View.VISIBLE else View.GONE
                }
            }
        }

    }

    private fun configureRecyclerView() {

        adapter = PostAdapter(actions = { post -> navigateToComments(post) })
        binding.listaPosts.layoutManager = LinearLayoutManager(context)
        binding.listaPosts.adapter = adapter

        binding.listaPosts.addItemDecoration(
            MarginItemDecoration(
                resources.getDimensionPixelSize(R.dimen.MedSize)
            )
        )
    }

    private fun navigateToComments(post: Post) {
        val action = PostFragmentDirections.actionMainFragmentToCommentFragment(post.id)
        findNavController().navigate(action)
    }

    private fun configAppBar() {

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)


    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_appbar_search, menu)
        val actionSearch = menu.findItem(R.id.search).actionView as SearchView

        actionSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    adapter.filterData(it)
                }
                return false
            }

        })
    }


    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

}