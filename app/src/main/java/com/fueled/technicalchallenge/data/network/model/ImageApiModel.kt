package com.fueled.technicalchallenge.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageApiModel(val path: String, val extension: String)
