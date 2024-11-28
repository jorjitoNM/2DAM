package com.example.apptareas.ui.events_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptareas.R
import com.example.apptareas.databinding.EventListFragmentBinding
import com.example.apptareas.domain.model.Event
import com.example.apptareas.ui.common.MarginItemDecoration
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.utilities.Constantes
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsListFragment : Fragment() {

    private val viewModel: EventListViewModel by viewModels ()
    private var _binding: EventListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EventAdapter
    private var userId :Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userId = arguments?.getInt(Constantes.USER_ID) ?: 1
        _binding = EventListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(EventListEvents.GetEvents)
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.events)

            state.appEvent?.let { appEvent ->
                if (appEvent is UiEvent.PopBackStack) {
                    findNavController().navigateUp()
                } else if (appEvent is UiEvent.ShowSnackbar) {
                    Snackbar.make(binding.root, appEvent.message, Snackbar.LENGTH_SHORT).show()
                }
                viewModel.handleEvent(EventListEvents.EventDone)
            }
        }
    }

    private fun configureRecyclerView() {
        adapter = EventAdapter(
            actions = object : EventAdapter.EventActions {
                override fun onItemClick(event : Event) {
                    navigateToDetail((event.id))
                }
                override fun updateEvent(event: Event) {
                    navigateToDetail(event.id)
                }
                override fun deleteEvent(event: Event) {
                    viewModel.handleEvent(EventListEvents.DeleteEvent(event))
                }
            },requireContext()
        )
        with (binding) {
            add.setOnClickListener {
                navigateToDetail(0)
            }
            eventsList.layoutManager = LinearLayoutManager(requireContext())
            eventsList.adapter = adapter
            eventsList.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.margin
                    )
                )
            )
        }
    }

    private fun navigateToDetail(id: Int) {
        findNavController().navigate(EventsListFragmentDirections.actionEventsListFragmentToEventDetailsFragment(id,userId));
    }
}