package com.example.myapplication.ui.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.CharacterListFragmentBinding
import com.example.myapplication.domain.model.Character
import com.example.myapplication.ui.common.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: CharacterListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CharacterAdapter
    private val viewModel: MainViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CharacterListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(MainEvents.GetCharacters)
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.characters)
        }
    }

    private fun configureRecyclerView() {
        adapter = CharacterAdapter(
            actions = object : CharacterAdapter.CharacterActions {
                override fun onItemClick(character: Character) {
                    navigateToDetail((character.id))
                }
            },requireContext()
        )
        with (binding) {
            characterList.layoutManager = LinearLayoutManager(requireContext())
            characterList.adapter = adapter
            characterList.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.margin
                    )
                )
            )
        }
    }

    private fun navigateToDetail(id: Int) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(id));
    }
}