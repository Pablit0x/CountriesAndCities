package domain.repository

import domain.model.Country

interface CountryRepository {
    suspend fun getAllCountries() : List<Country>
}