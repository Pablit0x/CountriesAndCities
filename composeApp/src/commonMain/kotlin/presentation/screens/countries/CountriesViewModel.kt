package presentation.screens.countries

import data.utils.Response
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountriesViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _state = MutableStateFlow(CountriesUiState(emptyList()))
    val state = _state.asStateFlow()

    fun updateCountries() {
        viewModelScope.launch {
            val countries = countryRepository.getAllCountries()

            when (countries) {
                is Response.Success -> {
                    _state.update {
                        it.copy(
                            countries = countries.data,
                            errorMsg = null
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            countries = emptyList(),
                            errorMsg = countries.message
                        )
                    }
                }
            }
        }
    }
}