package com.example.myapplication.ui.detailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.SongDetailsBinding
import com.example.myapplication.ui.common.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: SongDetailsBinding? = null
    private val binding get() = _binding!!
    private var id : String = ""

    private val viewModel: DetailsViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SongDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args : DetailsFragmentArgs by navArgs()
        super.onViewCreated(view, savedInstanceState)
        id = args.songId
        viewModel.handleEvent(DetailsEvents.GetSong(id))
        observarViewModel()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->

            if (state.event == null) {
                binding.songName.setText(state.song.name)
                binding.songArtist.setText(parseArtists(state.song.artist))
                binding.songDuration.setText(state.song.duration.toString())
                binding.explicit.check(fillCheckButton(state.song.explicit))
            }

            state.event?.let { event ->
                if (event is UiEvent.PopBackStack) {
                    findNavController().navigateUp()
                } else if (event is UiEvent.ShowSnackbar) {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                }
                viewModel.handleEvent(DetailsEvents.ErrorMostrado)
            }

            if (state.event == null)
                binding.songName.setText(state.song.name)
        }
    }

    private fun parseArtists(artist: List<String>): String {
        val sb : StringBuilder = StringBuilder()
        artist.forEach { a -> sb.append(a).append(",") }
        return sb.toString()
    }

    private fun fillCheckButton(explicit: Boolean): Int {
        return if (explicit) 0
        else 1
    }
}