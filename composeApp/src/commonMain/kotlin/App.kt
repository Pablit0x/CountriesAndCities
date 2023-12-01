import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
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
import data.repository.CountryRepositoryImpl
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import presentation.composables.CountryListItem
import presentation.screens.countries.CountriesViewModel

@Composable
fun App() {
    MaterialTheme {
        val countryRepository = CountryRepositoryImpl(countryApi = CountryApi())
        val countriesViewModel = getViewModel(Unit,
            viewModelFactory { CountriesViewModel(countryRepository = countryRepository) })
        val state by countriesViewModel.state.collectAsState()

        LaunchedEffect(countriesViewModel) {
            countriesViewModel.updateCountries()
        }

        AnimatedVisibility(state.countries.isNotEmpty(), enter = fadeIn()) {
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
}