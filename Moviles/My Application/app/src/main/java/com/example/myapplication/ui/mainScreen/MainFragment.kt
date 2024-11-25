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
import com.example.myapplication.databinding.SongsListFragmentBinding
import com.example.myapplication.domain.model.Song
import com.example.myapplication.ui.common.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: SongsListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SongAdapter
    private val viewModel: MainViewModel by viewModels ()
    private val token : String by lazy {
        viewModel.getToken()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SongsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(MainEvents.GetSongs(token))
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.songs)
            //comprobar si hay error y limpiarlo despues
        }
    }

    private fun configureRecyclerView() {
        adapter = SongAdapter(
            actions = object : SongAdapter.SongActions {
                override fun onItemClick(song: Song) {
                    navigateToDetail((song.id))
                }
            },requireContext()
        )
        with (binding) {
            songList.layoutManager = LinearLayoutManager(requireContext())
            songList.adapter = adapter
            songList.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.margin
                    )
                )
            )
        }
    }

    private fun navigateToDetail(id: String) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(id,token));
    }
}