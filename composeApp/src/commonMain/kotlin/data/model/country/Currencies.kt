package data.model.country

import kotlinx.serialization.Serializable

@Serializable
data class Currencies(
    val AUD: AUD = AUD(name = "unknown", "unknown")
)