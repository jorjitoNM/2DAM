package com.example.apptareas.ui.event_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apptareas.R
import com.example.apptareas.databinding.EventListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailsFragment : Fragment() {

    private val viewModel: EventDetailsViewModel by viewModels ()
    private var _binding: EventListFragmentBinding? = null
    private val binding get() = _binding!!
    private var userId : Int = -1
    private var eventId : Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userId = arguments?.getInt(R.string.user_id_argument.toString()) ?: -1
        eventId = arguments?.getInt(R.string.event_id_argument.toString()) ?: -1
        _binding = EventListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observarState()
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            if (state.appEvent == null) {
                with (binding) {
                    songName.setText(state.song.name)
                    songArtist.setText(parseArtists(state.song.artist))
                    songDuration.setText(state.song.duration.toString())
                    explicit.check(fillCheckButton(state.song.explicit))
                    image.load(state.song.albumImage)
                }
            }
        }
    }
}