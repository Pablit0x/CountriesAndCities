package presentation.screens.countries

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import data.remote.country.CountryApi
import data.repository.CountryRepositoryImpl
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import domain.repository.CountryRepository
import domain.shared.getCountryCachePath
import getPlatform
import org.koin.compose.koinInject
import presentation.composables.CountryListItem

class ScreenCountries : Screen {

    @Composable
    override fun Content() {
        val countryRepository = koinInject<CountryRepository>()
        val countriesViewModel = getViewModel(Unit,
            viewModelFactory { CountriesViewModel(countryRepository = countryRepository) })
        val state by countriesViewModel.state.collectAsState()
        val snackBarHostState = remember { SnackbarHostState() }

        LaunchedEffect(state.errorMsg) {
            if (state.errorMsg != null) {
                snackBarHostState.showSnackbar("Snackbar test")
            }
        }

        LaunchedEffect(Unit) {
            countriesViewModel.updateCountries()
        }

        Scaffold(
            snackbarHost = { SnackbarHost(snackBarHostState) }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                AnimatedVisibility(state.countries.isNotEmpty(), enter = fadeIn()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
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

    }
}