package com.fueled.technicalchallenge.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UrlApiModel(val type: String, val url: String)
