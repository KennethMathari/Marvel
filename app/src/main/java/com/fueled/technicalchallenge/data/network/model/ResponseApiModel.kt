package com.fueled.technicalchallenge.data.network.model

import kotlinx.serialization.Serializable

/**
 * Created by bhavya@fueled.com on 18/10/2024.
 */
@Serializable
data class ResponseApiModel<T>(val code: Int, val status: String, val etag: String, val data: T)