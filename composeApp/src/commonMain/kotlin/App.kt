import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import data.remote.country.CountryApi
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
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

        Text(
            text = "Countries = ${state.countries}",
            modifier = Modifier.verticalScroll(rememberScrollState())
        )
    }
}