package com.fueled.technicalchallenge.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fueled.technicalchallenge.domain.model.CharacterDomain
import com.fueled.technicalchallenge.presentation.viewmodel.CharacterListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterList(
    modifier: Modifier = Modifier,
    characterListViewModel: CharacterListViewModel = koinViewModel(),
    onCharacterClicked: (CharacterDomain) -> Unit
) {
    val characterListState by
    characterListViewModel.characterListState.collectAsStateWithLifecycle()
    var searchCharacter by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchCharacter,
            onValueChange = {
                searchCharacter = it
                characterListViewModel.onSearchCharacter(it)
            },
            label = { Text(text = "Search") },
            placeholder = { Text(text = "Search character...") },
            trailingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Character List
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(characterListState.characters ?: emptyList()) { characterDomain ->
                CharacterCard(
                    data = characterDomain,
                    onCharacterClicked = onCharacterClicked
                )
            }
        }
    }
}