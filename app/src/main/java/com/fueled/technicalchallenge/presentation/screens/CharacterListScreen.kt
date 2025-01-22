package com.fueled.technicalchallenge.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import com.fueled.technicalchallenge.presentation.components.CharacterDetails
import com.fueled.technicalchallenge.presentation.components.CharacterList
import com.fueled.technicalchallenge.presentation.mapper.toCharacterPresentation
import com.fueled.technicalchallenge.presentation.model.CharacterPresentation

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun CharacterListScreen() {
    val navigator = rememberListDetailPaneScaffoldNavigator<CharacterPresentation>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                CharacterList(onCharacterClicked = { characterDomain ->
                    navigator.navigateTo(
                        ListDetailPaneScaffoldRole.Detail, characterDomain.toCharacterPresentation()
                    )
                })
            }
        },
        detailPane = {
            AnimatedPane {
                navigator.currentDestination?.content?.let { characterPresentation ->
                    CharacterDetails(characterPresentation)
                }
            }
        }
    )
}

//    Box(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//        ) {
//            val charactersPerRow = 2
//            state.characters.chunked(charactersPerRow).forEach { rowItems ->
//                Row(modifier = Modifier.fillMaxWidth()) {
//                    rowItems.forEach { item ->
//                        Box(
//                            modifier = Modifier
//                                .weight(1f)
//                                .padding(8.dp)
//                        ) {
//                            CharacterCard(data = item)
//                        }
//                    }
//                    if (rowItems.size < charactersPerRow) {
//                        repeat(charactersPerRow - rowItems.size) {
//                            Spacer(modifier = Modifier.weight(1f))
//                        }
//                    }
//                }
//            }
//        }
//    }
