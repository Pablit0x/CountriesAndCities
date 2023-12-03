package di

import data.remote.country.CountryApi
import data.repository.CountryRepositoryImpl
import domain.repository.CountryRepository
import domain.shared.getCountryCachePath
import org.koin.dsl.module

val dataModule = module {
    single { CountryApi() }
    single { getCountryCachePath() }
    single<CountryRepository> { CountryRepositoryImpl(get(), get()) }
}