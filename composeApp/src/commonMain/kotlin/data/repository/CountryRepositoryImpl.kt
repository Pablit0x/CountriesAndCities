package data.repository

import data.remote.country.CountryApiServiceImpl
import data.utils.Response
import domain.model.Country
import domain.remote.CountryApiService
import domain.repository.CountryRepository
import domain.shared.CountryCachePath
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.extensions.getOrEmpty
import io.github.xxfast.kstore.file.storeOf

class CountryRepositoryImpl(
    private val countryApiServiceImpl: CountryApiService,
    pathToCountryCache: CountryCachePath
) : CountryRepository {

    private val cache: KStore<List<Country>> = storeOf(filePath = pathToCountryCache.name)

    override suspend fun getAllCountries(): Response<List<Country>> {
        val cachedCountries = cache.getOrEmpty()

        if (cachedCountries.isNotEmpty()) {
            return Response.Success(cachedCountries)
        }

        val apiResponse = countryApiServiceImpl.getAllCountries()

        if (apiResponse is Response.Success) {
            apiResponse.data.also { countries ->
                cache.set(countries)
            }
        }

        return apiResponse
    }
}