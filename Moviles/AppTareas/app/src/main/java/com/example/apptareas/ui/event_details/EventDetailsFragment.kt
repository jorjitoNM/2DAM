package com.example.apptareas.ui.event_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.apptareas.databinding.EventDetailsBinding
import com.example.apptareas.domain.model.Event
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.utilities.Constantes
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventDetailsFragment : Fragment() {

    private val viewModel: EventDetailsViewModel by viewModels ()
    private var _binding: EventDetailsBinding? = null
    private val binding get() = _binding!!
    private var userId : Int = 1
    private var eventId : Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userId = arguments?.getInt(Constantes.USER_ID) ?: 1
        eventId = arguments?.getInt(Constantes.EVENT_ID) ?: 1
        _binding = EventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observarState()
        with (binding) {
            update.setOnClickListener {
                viewModel.handleEvent(EventDetailsEvents.UpdateEvent(Event(eventId,eventTitle.text.toString(),eventBody.text.toString(),userId,image.toString())))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (userId != 0)
            viewModel.handleEvent(EventDetailsEvents.GetEvent(eventId))
    }

    private fun observarState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.appEvent == null) {
                        with(binding) {
                            eventTitle.setText(state.event.title)
                            eventBody.setText(state.event.body)
                            image.load(state.event.image) {
                                size(300, 500)
                            }
                        }
                    }

                    state.appEvent?.let { appEvent ->
                        if (appEvent is UiEvent.PopBackStack) {
                            findNavController().navigateUp()
                        } else if (appEvent is UiEvent.ShowSnackbar) {
                            Snackbar.make(binding.root, appEvent.message, Snackbar.LENGTH_SHORT)
                                .show()
                        }
                        viewModel.handleEvent(EventDetailsEvents.EventDone)
                    }
                }
            }
        }
    }
}