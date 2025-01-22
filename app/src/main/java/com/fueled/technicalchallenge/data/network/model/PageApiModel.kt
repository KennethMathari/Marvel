package com.fueled.technicalchallenge.data.network.model

import kotlinx.serialization.Serializable

/**
 * Created by bhavya@fueled.com on 17/10/2024.
 */
@Serializable
data class PageApiModel<T>(
    val offset: Int? = null,
    val limit: Int? = null,
    val total: Int? = null,
    val count: Int? = null,
    val results: List<T>? = null
)