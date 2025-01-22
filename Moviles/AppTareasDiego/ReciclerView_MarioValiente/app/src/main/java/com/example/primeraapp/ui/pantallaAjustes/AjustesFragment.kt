package com.example.primeraapp.ui.pantallaAjustes

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
import com.example.primeraapp.databinding.FragmentAjustesBinding
import com.example.primeraapp.domain.modelo.User
import com.example.primeraapp.ui.common.UiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AjustesFragment : Fragment() {

    private var _binding: FragmentAjustesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AjustesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observarViewModel()
        eventos()
        viewModel.handleEvent(AjustesEvent.GetUser(5))
    }

    private fun observarViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state.let {
                        state.event?.let { event ->
                            when (event) {
                                is UiEvent.ShowSnackbar -> {
                                    val mys = Snackbar.make(
                                        binding.root,
                                        event.message,
                                        Snackbar.LENGTH_SHORT
                                    )
                                    mys.show()
                                }

                                is UiEvent.PopBackStack -> findNavController().popBackStack()
                            }
                            viewModel.handleEvent(AjustesEvent.ErrorMostrado)
                            binding.progressBar.visibility =
                                if (state.isLoading) View.VISIBLE else View.GONE
                        }
                    }
                    if (state.event == null) {
                        state.user?.let {
                            binding.idUser.editText?.setText(state.user.id.toString())
                            binding.nameUser.editText?.setText(state.user.name)
                            binding.username.editText?.setText(state.user.username)
                            binding.email.editText?.setText(state.user.email)
                            binding.phone.editText?.setText(state.user.phone)
                            binding.website.editText?.setText(state.user.website)
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
                    AjustesEvent.UpdateUser(
                        User(
                            id = 5,
                            name = binding.nameUser.editText?.text.toString(),
                            username = binding.username.editText?.text.toString(),
                            email = binding.email.editText?.text.toString(),
                            phone = binding.phone.editText?.text.toString(),
                            website = binding.website.editText?.text.toString()
                        )
                    )
                )
            }
        }
    }
}
