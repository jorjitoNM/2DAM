package com.example.myapplication.ui.detailsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.domain.usecases.GetCharacter
import com.example.myapplication.ui.common.StringProvider
import com.example.myapplication.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (
    private val stringProvider: StringProvider,
    private val getCharacterUseCase: GetCharacter,
) : ViewModel() {

    private val _uiState = MutableLiveData(DetailsState())
    val uiState: LiveData<DetailsState> get() = _uiState

    fun handleEvent (event : DetailsEvents) {
        when (event) {
            is DetailsEvents.GetCharacter -> getCharacter(event.characterId)
            is DetailsEvents.EventDone -> eventDone()
        }
    }


    private fun getCharacter(id: Int) {
        viewModelScope.launch {
            when (val characterRemote = getCharacterUseCase(id)) {
                is NetworkResult.Error -> _uiState.value =
                    _uiState.value?.copy(event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.songNotFound)))
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Success -> _uiState.value = characterRemote.data.let { it?.let { it1 -> _uiState.value?.copy(character = it1) } }
            }
        }
    }

    private fun eventDone() {
        _uiState.value = _uiState.value?.copy(event = null)
    }
}