import data.remote.country.CountryApi
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountriesViewModel(private val countryApi: CountryApi) : ViewModel() {

    private val _state = MutableStateFlow(CountriesUiState(emptyList()))
    val state = _state.asStateFlow()

    fun updateCountries() {
        viewModelScope.launch {
            val countries = countryApi.getAllCountries()
            _state.update {
                it.copy(
                    countries = countries
                )
            }
        }
    }
}