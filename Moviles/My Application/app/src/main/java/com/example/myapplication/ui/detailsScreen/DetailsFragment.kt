package com.example.myapplication.ui.detailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.myapplication.databinding.SongDetailsBinding
import com.example.myapplication.ui.common.UiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: SongDetailsBinding? = null
    private val binding get() = _binding!!
    private var id : String = ""
    private var token : String = ""

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
        token = args.token
        viewModel.handleEvent(DetailsEvents.GetSong(id,token))
        observarViewModel()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->

            if (state.event == null) {
                with (binding) {
                    songName.setText(state.song.name)
                    songArtist.setText(parseArtists(state.song.artist))
                    songDuration.setText(state.song.duration.toString())
                    explicit.check(fillCheckButton(state.song.explicit))
                    image.load(state.song.albumImage)
                }
            }

            state.event?.let { event ->
                if (event is UiEvent.PopBackStack) {
                    findNavController().navigateUp()
                } else if (event is UiEvent.ShowSnackbar) {
                    Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT).show()
                }
                viewModel.handleEvent(DetailsEvents.EventDone)
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