package com.fueled.technicalchallenge.data.mapper

import com.fueled.technicalchallenge.data.local.entity.CharacterEntity
import com.fueled.technicalchallenge.domain.model.CharacterDomain

fun CharacterDomain.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        defaultImageUrl = this.defaultImageUrl
    )
}