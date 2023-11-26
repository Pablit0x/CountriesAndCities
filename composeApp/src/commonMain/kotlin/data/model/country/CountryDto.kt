package data.model.country

import domain.model.Country
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    val area: Double,
    val capital: List<String> = emptyList(),
    val cca2: String,
    val continents: List<String>,
    val currencies: Currencies = Currencies(),
    val flags: Flags,
    val languages: Languages = Languages(),
    val name: Name,
    val population: Int,
    val timezones: List<String>,
    val translations: Translations,
)

fun CountryDto.toCountry(): Country {
    return Country(name = translations.pol.official)
}