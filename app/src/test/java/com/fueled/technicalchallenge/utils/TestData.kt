package com.fueled.technicalchallenge.utils

import com.fueled.technicalchallenge.domain.model.CharacterDomain
import com.hannesdorfmann.instantiator.instance

object TestData {

    val characterDomain: CharacterDomain = instance()

    val error: Throwable = instance()
}