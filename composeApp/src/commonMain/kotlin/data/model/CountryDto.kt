package data.model

import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    val code: String,
    val code3: String,
    val continent: String,
    val currency: String,
    val name: String,
    val names: NamesDto,
    val population: Int
)