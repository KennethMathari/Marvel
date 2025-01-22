package com.fueled.technicalchallenge.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ResourceItemsApiModel(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<ResourceApiModel>
)
