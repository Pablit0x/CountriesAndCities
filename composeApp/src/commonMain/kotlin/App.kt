import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import di.dataModule
import org.koin.compose.KoinApplication
import presentation.screens.menu.ScreenMenu

@Composable
fun App() {
    KoinApplication(moduleList = {
        listOf(dataModule)
    }) {
        MaterialTheme {
            Navigator(screen = ScreenMenu()) { navigator ->
                SlideTransition(navigator = navigator)
            }
        }
    }
}