import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.remote.country.CountryApi
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import presentation.composables.CountryListItem
import presentation.countries.CountriesViewModel

@Composable
fun App() {

    val countryApi by lazy {
        CountryApi()
    }

    MaterialTheme {
        val countriesViewModel =
            getViewModel(Unit, viewModelFactory { CountriesViewModel(countryApi = countryApi) })
        val state by countriesViewModel.state.collectAsState()

        LaunchedEffect(countriesViewModel) {
            countriesViewModel.updateCountries()
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(state.countries) { country ->
                CountryListItem(
                    countryName = country.name,
                    flagImage = country.flag,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}