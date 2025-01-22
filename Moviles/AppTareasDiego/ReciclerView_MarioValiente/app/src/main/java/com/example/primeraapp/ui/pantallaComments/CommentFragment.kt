package com.example.primeraapp.ui.pantallaComments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.primeraapp.R
import com.example.primeraapp.databinding.FragmentCommentsBinding
import com.example.primeraapp.ui.common.ConstantesUI
import com.example.primeraapp.ui.common.MarginItemDecoration
import com.example.primeraapp.ui.common.UiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommentFragment : Fragment() {
    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CommentAdapter
    private val viewModel: CommentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            val postId = CommentFragmentArgs.fromBundle(it).id
            viewModel.handleEvent(CommentEvent.GetComments(postId))
        }
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CommentAdapter()
        configureRecyclerView()
        observarState()
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
                                viewModel.handleEvent(CommentEvent.UndoDelete)
                            }
                            mys.show()
                        }
                        viewModel.handleEvent(CommentEvent.ErrorMostrado)
                    }
                    adapter.submitList(state.comments)
                    binding.progressBar.visibility =
                        if (state.isLoading) View.VISIBLE else View.GONE
                }
            }
        }

    }

    private fun configureRecyclerView() {
        binding.listaComments.layoutManager = LinearLayoutManager(context)
        binding.listaComments.adapter = adapter
        binding.listaComments.addItemDecoration(
            MarginItemDecoration(
                resources.getDimensionPixelSize(R.dimen.MedSize)
            )
        )
    }

}
