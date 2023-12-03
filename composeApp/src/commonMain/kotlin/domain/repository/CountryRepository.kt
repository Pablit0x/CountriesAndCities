package domain.repository

import data.utils.Response
import domain.model.Country

interface CountryRepository {
    suspend fun getAllCountries(): Response<List<Country>>
}