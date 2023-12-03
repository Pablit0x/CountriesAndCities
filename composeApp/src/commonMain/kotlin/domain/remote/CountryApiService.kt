package domain.remote

import data.utils.Response
import domain.model.Country

interface CountryApiService {
    suspend fun getAllCountries(): Response<List<Country>>
}