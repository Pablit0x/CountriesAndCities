package data.model.country

import kotlinx.serialization.Serializable

@Serializable
data class Languages(
    val eng: String = "unknown"
)