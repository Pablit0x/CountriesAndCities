package data.repository

import data.remote.country.CountryApi
import domain.model.Country
import domain.repository.CountryRepository
import domain.shared.CountryCachePath
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf

class CountryRepositoryImpl(
    private val countryApi: CountryApi, pathToCountryCache: CountryCachePath
) : CountryRepository {

    private val cache: KStore<List<Country>> = storeOf(filePath = pathToCountryCache.name)

    override suspend fun getAllCountries(): List<Country> {
        return countryApi.getAllCountries().also { listOfCountries ->
            cache.set(listOfCountries)
        }
    }
}