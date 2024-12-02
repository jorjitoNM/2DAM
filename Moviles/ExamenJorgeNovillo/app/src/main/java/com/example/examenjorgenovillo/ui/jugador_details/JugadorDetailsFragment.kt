package com.example.examenjorgenovillo.ui.jugador_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apptareas.databinding.JugadorDetailsBinding
import com.example.examenjorgenovillo.domain.model.Jugador
import com.example.examenjorgenovillo.ui.common.UiEvent
import com.example.examenjorgenovillo.utilities.Constantes
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JugadorDetailsFragment : Fragment() {

    private val viewModel: JugadoresDetailsViewModel by viewModels ()
    private var _binding: JugadorDetailsBinding? = null
    private val binding get() = _binding!!
    private var equipoId : Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        equipoId = arguments?.getInt(Constantes.EQUIPO_ID) ?: 1
        _binding = JugadorDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observarState()
    }

    override fun onResume() {
        super.onResume()
        with (binding) {
            viewModel.handleEvent(JugadorDetailsEvents.addJugador(equipoId,
                Jugador(nameText.text.toString(),surenameText.text.toString(),dorsalText.text.toString().toInt())))
        }
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            if (state.event == null) {
                with (binding) {
                    nameText.setText(state.jugador.nombre)
                    surenameText.setText(state.jugador.apellido)
                    dorsalText.setText(state.jugador.dorsal.toString())
                }
            }

            state.event?.let { event ->
                if (event is UiEvent.PopBackStack) {
                    findNavController().navigateUp()
                } else if (event is UiEvent.ShowSnackbar) {
                    Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT).show()
                }
                viewModel.handleEvent(JugadorDetailsEvents.eventDone)
            }
        }
    }
}