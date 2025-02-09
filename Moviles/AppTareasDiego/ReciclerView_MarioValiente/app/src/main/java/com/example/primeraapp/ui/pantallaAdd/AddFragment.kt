package com.example.primeraapp.ui.pantallaAdd

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
import androidx.navigation.fragment.findNavController
import com.example.primeraapp.databinding.FragmentAddBinding
import com.example.primeraapp.domain.modelo.Post
import com.example.primeraapp.ui.common.ConstantesUI
import com.example.primeraapp.ui.common.UiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.userIdText.setText(ConstantesUI.TEXT_VALUE)
        binding.idText.setText(ConstantesUI.TEXT_VALUE)
        eventos()
        observarViewModel()
    }

    private fun observarViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state.let {
                        state.event?.let { event ->
                            when (event) {
                                UiEvent.PopBackStack -> findNavController().popBackStack()
                                is UiEvent.ShowSnackbar -> {
                                    val mys = Snackbar.make(
                                        binding.root,
                                        event.message,
                                        Snackbar.LENGTH_SHORT
                                    )
                                    mys.show()
                                }
                            }
                            viewModel.handleEvent(AddEvent.ErrorMostrado)
                            binding.progressBar.visibility =
                                if (state.isLoading) View.VISIBLE else View.GONE
                        }
                    }
                }
            }
        }

    }

    private fun eventos() {
        with(binding) {
            loginB.setOnClickListener {
                viewModel.handleEvent(
                    AddEvent.AddPost(
                        Post(
                            userId = 0,
                            id = 0,
                            title = binding.title.editText?.text.toString(),
                            body = binding.body.editText?.text.toString()
                        )
                    )
                )
            }
        }
    }
}