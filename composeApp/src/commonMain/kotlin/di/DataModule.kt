package di

import data.remote.country.CountryApiServiceImpl
import data.repository.CountryRepositoryImpl
import domain.remote.CountryApiService
import domain.repository.CountryRepository
import domain.shared.getCountryCachePath
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}

val dataModule = module {
    single<CountryApiService> { CountryApiServiceImpl(httpClient) }
    single { getCountryCachePath() }
    single<CountryRepository> { CountryRepositoryImpl(get(), get()) }
}
