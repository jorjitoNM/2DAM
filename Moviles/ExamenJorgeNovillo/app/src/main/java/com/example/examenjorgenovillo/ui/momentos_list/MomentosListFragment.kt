package com.example.examenjorgenovillo.ui.momentos_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptareas.R
import com.example.apptareas.databinding.MomentosListBinding
import com.example.examenjorgenovillo.domain.model.Momento
import com.example.examenjorgenovillo.ui.common.MarginItemDecoration
import com.example.examenjorgenovillo.ui.common.UiEvent
import com.example.examenjorgenovillo.utilities.Constantes
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MomentosListFragment : Fragment() {

    private val viewModel: MomentoListViewModel by viewModels ()
    private var _binding: MomentosListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MomentoAdapter
    private var equipoId : Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        equipoId = arguments?.getInt(Constantes.EQUIPO_ID) ?: 1
        _binding = MomentosListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(MomentoListEvents.getMomentos(equipoId))
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.momentos)

            state.event?.let { event ->
                if (event is UiEvent.PopBackStack) {
                    findNavController().navigateUp()
                } else if (event is UiEvent.ShowSnackbar) {
                    Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT).show()
                }
                viewModel.handleEvent(MomentoListEvents.eventDone)
            }
        }
    }

    private fun configureRecyclerView() {
        adapter = MomentoAdapter(
            actions = object : MomentoAdapter.MomentoActions {
            override fun onItemClick(momento : Momento) {
                navigateToDetail((momento.id))
            }
        }
            ,requireContext())
        with (binding) {
            momentosList.layoutManager = LinearLayoutManager(requireContext())
            momentosList.adapter = adapter
            momentosList.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.margin
                    )
                )
            )
        }
    }

    private fun navigateToDetail(id : Int) {
        findNavController().navigate(MomentosListFragmentDirections.actionMomentosListFragmentToMomentoDetailsFragment(equipoId,id));
    }
}