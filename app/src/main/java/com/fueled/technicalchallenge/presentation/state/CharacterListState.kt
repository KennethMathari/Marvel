package com.fueled.technicalchallenge.presentation.state

import com.fueled.technicalchallenge.domain.model.CharacterDomain

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<CharacterDomain>? = null,
    var error: String? = null
)
