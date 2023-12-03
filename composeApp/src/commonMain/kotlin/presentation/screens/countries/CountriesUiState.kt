package presentation.screens.countries

import domain.model.Country

data class CountriesUiState(
    val countries: List<Country>,
    val errorMsg : String? = null
)