package com.fueled.technicalchallenge.data.network.model

import com.fueled.technicalchallenge.domain.model.ImageVariant
import kotlinx.serialization.Serializable

/**
 * Created by bhavya@fueled.com on 17/10/2024.
 */
@Serializable
data class CharacterApiModel(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: ImageApiModel,
    val resourceURI: String,
    val events: ResourceItemsApiModel,
    val urls: List<UrlApiModel>
) {
    val defaultImageUrl =
        "${thumbnail.path}/${ImageVariant.PORTRAIT_INCREDIBLE.pathValue}.${thumbnail.extension}"
            .formatUrl()
}

private fun String.formatUrl(): String {
    return when {
        startsWith("https") -> this
        startsWith("http") -> replace("http", "https")
        else -> "https://$this"
    }
}