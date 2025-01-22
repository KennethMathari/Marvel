package com.fueled.technicalchallenge.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ResourceApiModel(val resourceURI: String, val name: String, val type: String? = null)
