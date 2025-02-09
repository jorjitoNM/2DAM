package com.example.examenjorgenovillo.ui.jugadores_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptareas.R
import com.example.apptareas.databinding.JugadoresListBinding
import com.example.examenjorgenovillo.domain.model.Jugador
import com.example.examenjorgenovillo.ui.common.MarginItemDecoration
import com.example.examenjorgenovillo.ui.common.UiEvent
import com.example.examenjorgenovillo.utilities.Constantes
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JugadoresListFragment : Fragment() {

    private val viewModel: JugadoresListViewModel by viewModels ()
    private var _binding: JugadoresListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: JugadorAdapter
    private var equipoId : Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        equipoId = arguments?.getInt(Constantes.EQUIPO_ID) ?: 1
        _binding = JugadoresListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(JugadoresListEvents.getJugadores(equipoId))
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.jugadores)

            state.event?.let { event ->
                if (event is UiEvent.PopBackStack) {
                    findNavController().navigateUp()
                } else if (event is UiEvent.ShowSnackbar) {
                    Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT).show()
                }
                viewModel.handleEvent(JugadoresListEvents.eventDone)
            }
        }
    }

    private fun configureRecyclerView() {
        adapter = JugadorAdapter(requireContext()
        )
        with (binding) {
            jugadoresList.layoutManager = LinearLayoutManager(requireContext())
            jugadoresList.adapter = adapter
            jugadoresList.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.margin
                    )
                )
            )
            add.setOnClickListener {
                navigateToDetail()
            }
        }
    }

    private fun navigateToDetail() {
        findNavController().navigate(JugadoresListFragmentDirections.actionJugadoresListFragmentToJugadorDetailsFragment(equipoId));
    }
}