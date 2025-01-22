package com.fueled.technicalchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fueled.technicalchallenge.domain.repository.CharactersRepository
import com.fueled.technicalchallenge.presentation.state.CharacterListState
import com.fueled.technicalchallenge.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _characterListState = MutableStateFlow(CharacterListState())
    val characterListState: StateFlow<CharacterListState> get() = _characterListState.asStateFlow()

    private val _searchCharacter = MutableStateFlow("")

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _characterListState.value = CharacterListState(
                isLoading = true, characters = null, error = null
            )

            charactersRepository.getCharacters().collect { result ->
                when (result) {
                    is NetworkResult.ClientError -> {
                        updateErrorMessage("Unable to get Character List! Please Try Again.")
                    }
                    is NetworkResult.NetworkError -> {
                        updateErrorMessage("Check Internet Connection.")
                    }
                    is NetworkResult.ServerError -> {
                        updateErrorMessage("Oops! Our Server is Down.")
                    }

                    is NetworkResult.Success -> {
                        _characterListState.value = CharacterListState(
                            isLoading = false, characters = result.data, error = null
                        )
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(errorMessage: String) {
        _characterListState.value = CharacterListState(
            isLoading = false, characters = null, error = errorMessage
        )
    }

    fun onSearchCharacter(query: String) {
        _searchCharacter.value = query
        applySearchFilter()
    }

    private fun applySearchFilter() {
        val query = _searchCharacter.value.lowercase()
        val filteredCharacterList = _characterListState.value.characters?.filter {
            it.name.lowercase().contains(query)
        }

        _characterListState.value = CharacterListState(
            isLoading = false, error = null, characters = filteredCharacterList
        )
    }
}