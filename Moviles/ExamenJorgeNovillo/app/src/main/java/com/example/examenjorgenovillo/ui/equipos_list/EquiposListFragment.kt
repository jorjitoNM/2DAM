package com.example.examenjorgenovillo.ui.equipos_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptareas.R
import com.example.apptareas.databinding.EquiposListBinding
import com.example.examenjorgenovillo.domain.model.Equipo
import com.example.examenjorgenovillo.ui.common.MarginItemDecoration
import com.example.examenjorgenovillo.ui.common.UiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EquiposListFragment : Fragment() {

    private val viewModel: EquiposListViewModel by viewModels ()
    private var _binding: EquiposListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EquipoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EquiposListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(EquiposListEvents.getEquipos)
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.equipos)

            state.event?.let { event ->
                if (event is UiEvent.PopBackStack) {
                    findNavController().navigateUp()
                } else if (event is UiEvent.ShowSnackbar) {
                    Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT).show()
                }
                viewModel.handleEvent(EquiposListEvents.eventDone)
            }
        }
    }

    private fun configureRecyclerView() {
        adapter = EquipoAdapter(
            actions = object : EquipoAdapter.EquipoActions {
                override fun onItemClick(equipo : Equipo) {
                    navigateToDetail((equipo.id))
                }
            },requireContext()
        )
        with (binding) {
            equiposList.layoutManager = LinearLayoutManager(requireContext())
            equiposList.adapter = adapter
            equiposList.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.margin
                    )
                )
            )
        }
    }

    private fun navigateToDetail(id: Int) {
        findNavController().navigate(EquiposListFragmentDirections.actionEquiposListFragmentToJugadoresListFragment(id));
    }
}