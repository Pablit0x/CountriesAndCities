package data.repository

import data.remote.country.CountryApi
import domain.model.Country
import domain.repository.CountryRepository

class CountryRepositoryImpl(private val countryApi: CountryApi) : CountryRepository {
    override suspend fun getAllCountries(): List<Country> {
        return countryApi.getAllCountries()
    }
}