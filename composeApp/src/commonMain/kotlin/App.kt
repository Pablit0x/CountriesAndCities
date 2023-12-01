import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import presentation.screens.menu.ScreenMenu

@Composable
fun App() {
    MaterialTheme {
        Navigator(screen = ScreenMenu()) { navigator ->
            SlideTransition(navigator = navigator)
        }
    }
}