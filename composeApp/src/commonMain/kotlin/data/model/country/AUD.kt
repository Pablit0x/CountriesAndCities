package data.model.country

import kotlinx.serialization.Serializable

@Serializable
data class AUD(
    val name: String,
    val symbol: String
)