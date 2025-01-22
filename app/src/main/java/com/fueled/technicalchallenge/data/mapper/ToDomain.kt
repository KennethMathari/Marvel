package com.fueled.technicalchallenge.data.mapper

import com.fueled.technicalchallenge.data.local.entity.CharacterEntity
import com.fueled.technicalchallenge.data.network.model.CharacterApiModel
import com.fueled.technicalchallenge.domain.model.CharacterDomain

fun CharacterEntity.toCharacterDomain(): CharacterDomain {
    return CharacterDomain(
        id = this.id,
        name = this.name,
        description = this.description,
        defaultImageUrl = this.defaultImageUrl
    )
}

fun CharacterApiModel.toCharacterDomain(): CharacterDomain {
    return CharacterDomain(
        id = this.id.toString(),
        name = this.name,
        description = this.description,
        defaultImageUrl = this.defaultImageUrl
    )
}