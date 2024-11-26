package com.example.apptareas.ui.user_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.apptareas.R
import com.example.apptareas.databinding.UserProfileBinding
import com.example.apptareas.ui.todos_list.TodoAdapter
import com.example.apptareas.utilities.Constantes

class ProfileFragment : Fragment() {

    private val viewModel: ProfileFragmentViewModel by viewModels()
    private var _binding: UserProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TodoAdapter
    private var userId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userId = arguments?.getInt(R.string.user_id_argument.toString()) ?: -1
        _binding = UserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleEvent(ProfileFragmentEvents.GetUser(userId))
        observarState()
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            if (state.appEvent == null) {
                with (binding) {
                    username.setText(state.user.username)
                    name.setText(state.user.name)
                    userId.setText(state.user.id)
                    image.load(Constantes.IMAGE_PROVIDER)
                }
            }
        }
    }
}