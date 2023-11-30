package data.remote.country

import data.model.country.CountryDto
import data.model.country.toCountry
import data.utils.Constants
import domain.model.Country
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CountryApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
                isLenient = true
                coerceInputValues = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getAllCountries(): List<Country> {
        return httpClient.get(Constants.BASE_URL).body<List<CountryDto>>().sortedBy {
            it.translations.pol.common
        }.map { countryDto ->
            countryDto.toCountry()
        }
    }
}