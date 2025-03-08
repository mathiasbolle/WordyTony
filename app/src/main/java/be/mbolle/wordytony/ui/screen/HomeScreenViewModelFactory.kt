package be.mbolle.wordytony.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.mbolle.wordytony.data.datastore.UserPreferencesRepository

class HomeScreenViewModelFactory(private val repository: UserPreferencesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserPreferencesRepository::class.java)
            .newInstance(repository)
    }
}
