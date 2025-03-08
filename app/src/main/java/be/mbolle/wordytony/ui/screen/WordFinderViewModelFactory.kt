package be.mbolle.wordytony.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.mbolle.wordytony.data.datastore.UserPreferencesRepository
import be.mbolle.wordytony.model.Level

class WordFinderViewModelFactory(private val level: Level, private val repository: UserPreferencesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordFinderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordFinderViewModel(
                userPreferencesRepository = repository,
                level = level,
            ) as T
        }
        throw IllegalArgumentException("Unknown VM")
    }
}
