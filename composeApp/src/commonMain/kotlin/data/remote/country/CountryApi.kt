package data.remote.country

import data.model.country.CountryDto
import data.model.country.toCountry
import data.utils.Constants
import data.utils.Response
import domain.model.Country
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.json.Json

class CountryApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getAllCountries(): Response<List<Country>> {
        return try {
            Response.Success(
                httpClient.get(Constants.BASE_URL).body<List<CountryDto>>().sortedBy {
                    it.translations.pol.common
                }.map { countryDto ->
                    countryDto.toCountry()
                }
            )
        } catch (e: IOException) {
            Response.Error("Network error: ${e.message}")
        } catch (e: ClientRequestException) {
            Response.Error("Client request error: ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            Response.Error("Server response error: ${e.response.status.description}")
        } catch (e: HttpRequestTimeoutException) {
            Response.Error("Request timeout: ${e.message}")
        } catch (e: Exception) {
            Response.Error("Unexpected error: ${e.message}")
        }
    }

}