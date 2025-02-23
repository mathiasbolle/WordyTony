package be.mbolle.wordytony.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {
    var showBottomSheet by mutableStateOf(false)
        private set

    fun showBottomSheet() {
        showBottomSheet = true
    }

    fun hideBottomSheet() {
        showBottomSheet = false
    }
}