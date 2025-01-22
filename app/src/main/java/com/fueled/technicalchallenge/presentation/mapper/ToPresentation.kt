package com.fueled.technicalchallenge.presentation.mapper

import com.fueled.technicalchallenge.domain.model.CharacterDomain
import com.fueled.technicalchallenge.presentation.model.CharacterPresentation

fun CharacterDomain.toCharacterPresentation(): CharacterPresentation {
    return CharacterPresentation(
        description = this.description,
        defaultImageUrl = this.defaultImageUrl,
        id = this.id,
        name = this.name
    )
}