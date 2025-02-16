package be.mbolle.wordytony.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeScreenViewModel(): ViewModel() {


    var navigationState by mutableStateOf<NavigationEvents>(NavigationEvents.HomeScreen)
        private set


    fun navToPlayScreen() {
        navigationState = NavigationEvents.PlayScreen
    }

}

sealed class NavigationEvents {
    data object PlayScreen: NavigationEvents()
    data object HomeScreen: NavigationEvents()
}