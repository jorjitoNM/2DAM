package com.example.apptareas.ui.user_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.utilities.Constantes
import com.example.primerxmlmvvm.databinding.UserProfileBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileFragmentViewModel by viewModels()
    private var _binding: UserProfileBinding? = null
    private val binding get() = _binding!!
    private var userId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userId = arguments?.getInt(Constantes.USER_ID) ?: 1
        _binding = UserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleEvent(ProfileFragmentEvents.GetUser(userId))
        observarState()
    }

    private fun observarState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.appEvent == null) {
                        with(binding) {
                            username.setText(state.user.username)
                            name.setText(state.user.name)
                            userId.text = state.user.id.toString()
                            image.load(Constantes.IMAGE_PROVIDER) {
                                size(300, 500)
                            }
                        }
                    }

                    state.appEvent?.let { appEvent ->
                        if (appEvent is UiEvent.ShowSnackbar) {
                            Snackbar.make(binding.root, appEvent.message, Snackbar.LENGTH_SHORT)
                                .show()
                        }
                        viewModel.handleEvent(ProfileFragmentEvents.EventDone)
                    }
                }
            }
        }
    }
}