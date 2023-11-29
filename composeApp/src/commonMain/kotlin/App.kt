import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.remote.country.CountryApi
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {

    val countryApi by lazy {
        CountryApi()
    }

    MaterialTheme {
        val countriesViewModel = getViewModel(Unit, viewModelFactory { CountriesViewModel(countryApi = countryApi) })
        val state by countriesViewModel.state.collectAsState()

        LaunchedEffect(countriesViewModel){
            countriesViewModel.updateCountries()
        }

        Text(text = "Countries = ${state.countries}", modifier = Modifier.verticalScroll(rememberScrollState()))
    }
}