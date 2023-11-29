import data.utils.Constants
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.model.Country
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

class CountriesViewModel: ViewModel() {
    private val httpClient = HttpClient() {
        install(ContentNegotiation){
            json()
        }
    }
    
    private suspend fun getCountries() : List<Country> {
        val key =  "43ad7ddc-2699-4ea7-b081-cecc5cedba42"
    }

    override fun onCleared() {
        httpClient.close()
    }
}