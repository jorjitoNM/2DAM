package com.example.primeraapp.ui.pantallaPostsOfUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.primeraapp.R
import com.example.primeraapp.databinding.FragmentPostUserBinding
import com.example.primeraapp.ui.common.ConstantesUI
import com.example.primeraapp.ui.common.MarginItemDecoration
import com.example.primeraapp.ui.common.UiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostUserFragment : Fragment() {
    private var _binding: FragmentPostUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PostUserAdapter
    private val viewModel: PostUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        configureRecyclerView()
        observarState()
        viewModel.handleEvent(PostUserEvent.GetPostsOfUser(5))
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
                                viewModel.handleEvent(PostUserEvent.UndoDelete)
                            }
                            mys.show()
                        }
                        viewModel.handleEvent(PostUserEvent.ErrorMostrado)
                    }
                    adapter.submitList(state.posts)
                    binding.progressBar.visibility =
                        if (state.isLoading) View.VISIBLE else View.GONE
                }
            }
        }

    }

    private fun configureRecyclerView() {
        adapter = PostUserAdapter(
            context = requireContext(),
            actions = { post -> viewModel.handleEvent(PostUserEvent.DeletePersona(post.id)) }
        )
        binding.listaPostsUser.layoutManager = LinearLayoutManager(context)
        binding.listaPostsUser.adapter = adapter

        binding.listaPostsUser.addItemDecoration(
            MarginItemDecoration(
                resources.getDimensionPixelSize(R.dimen.MedSize)
            )
        )
        val itemTouchHelper = ItemTouchHelper(adapter.swipeGesture)
        itemTouchHelper.attachToRecyclerView(binding.listaPostsUser)
    }
}