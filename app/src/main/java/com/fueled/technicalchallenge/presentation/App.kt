package com.fueled.technicalchallenge.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fueled.technicalchallenge.presentation.components.ErrorScreen
import com.fueled.technicalchallenge.presentation.components.Loading
import com.fueled.technicalchallenge.presentation.screens.CharacterListScreen
import com.fueled.technicalchallenge.presentation.viewmodel.CharacterListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun App(
    characterListViewModel: CharacterListViewModel = koinViewModel()
) {
    val characterListState by
    characterListViewModel.characterListState.collectAsStateWithLifecycle()

    when {
        characterListState.isLoading -> {
            Loading()
        }

        characterListState.error != null -> {
            ErrorScreen(errorMessage = characterListState.error ?: "")
        }

        characterListState.characters != null -> {
            characterListState.characters?.let {
                CharacterListScreen()
            }
        }
    }
}