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
import com.example.myapplication.databinding.CharacterDetailsBinding
import com.example.myapplication.ui.common.UiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: CharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private var id : Int = -1

    private val viewModel: DetailsViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args : DetailsFragmentArgs by navArgs()
        super.onViewCreated(view, savedInstanceState)
        id = args.characterId
        viewModel.handleEvent(DetailsEvents.GetCharacter(id))
        observarViewModel()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->

            if (state.event == null) {
                with (binding) {
                    characterName.setText(state.character.name)
                    characterSpecies.setText(state.character.species)
                    alive.check(fillCheckButton(state.character.alive))
                    image.load(state.character.image)
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
                binding.characterName.setText(state.character.name)
        }
    }

    private fun fillCheckButton(explicit: Boolean): Int {
        return if (explicit) 0
        else 1
    }
}