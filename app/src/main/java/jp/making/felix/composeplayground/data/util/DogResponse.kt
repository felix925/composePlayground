package jp.making.felix.composeplayground.data.util

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogResponse(
    @Json(name = "message")
    val message: List<String>,
    @Json(name = "status")
    val status: String
)