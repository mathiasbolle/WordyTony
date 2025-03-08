package be.mbolle.wordytony.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.mbolle.wordytony.data.datastore.UserPreferencesRepository
import be.mbolle.wordytony.model.Level
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    var showBottomSheet by mutableStateOf(false)
        private set

    var level: Level? = null
        private set

    fun restoreCache() {
        viewModelScope.launch {
            val cachedLevel = userPreferencesRepository.getLevel()
            Log.d("HomeScreenViewModel", "The cached level is $cachedLevel")

            if (cachedLevel != null) {
                level = cachedLevel
            }
        }
    }

    fun showBottomSheet() {
        showBottomSheet = true
    }

    fun hideBottomSheet() {
        showBottomSheet = false
    }

    fun persistLevel(level: Level) {
        viewModelScope.launch {
            userPreferencesRepository.setLevel(level)
        }
    }
}