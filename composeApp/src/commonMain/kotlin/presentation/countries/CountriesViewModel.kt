import data.model.country.CountryDto
import data.remote.country.CountryApi
import data.utils.Constants
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class CountriesViewModel(private val countryApi: CountryApi): ViewModel() {

    private val _state = MutableStateFlow(CountriesUiState(emptyList()))
    val state = _state.asStateFlow()
    
    fun updateCountries(){
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