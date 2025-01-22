package com.fueled.technicalchallenge.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterPresentation(
    val description: String,
    val id: String,
    val name: String,
    val defaultImageUrl: String
) : Parcelable
