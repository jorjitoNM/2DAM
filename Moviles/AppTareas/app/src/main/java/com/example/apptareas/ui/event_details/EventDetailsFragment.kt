package com.example.apptareas.ui.event_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.apptareas.R
import com.example.apptareas.databinding.EventDetailsBinding
import com.example.apptareas.domain.model.Event
import dagger.hilt.android.AndroidEntryPoint

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
        userId = arguments?.getInt(R.string.user_id_argument.toString()) ?: 1
        eventId = arguments?.getInt(R.string.event_id_argument.toString()) ?: 1
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
        viewModel.handleEvent(EventDetailsEvents.GetEvent(eventId))
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            if (state.appEvent == null) {
                with (binding) {
                    eventTitle.setText(state.event.title)
                    eventBody.setText(state.event.body)
                    image.load(state.event.image)
                }
            }
        }
    }
}