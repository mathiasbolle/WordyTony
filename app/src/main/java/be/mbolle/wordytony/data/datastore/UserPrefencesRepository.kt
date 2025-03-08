package be.mbolle.wordytony.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import be.mbolle.wordytony.data.toData
import be.mbolle.wordytony.data.toModel
import be.mbolle.wordytony.model.Level
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull

interface UserPreferencesRepository {
    suspend fun getLevel(): Level?

    suspend fun setLevel(level: Level)
}

class DatastoreUserPreferencesRepository(
    private val context: Context,
) : UserPreferencesRepository {
    private val Context.userPreferences: DataStore<UserPreferences> by dataStore(
        fileName = "user_pref.pb",
        serializer = UserPreferencesSerializer,
    )

    val userPreferencesFlow: Flow<UserPreferences> =
        context.userPreferences.data
            .catch { exception ->
                if (exception is IOException) {
                    // Log.e(TAG, "Error reading sort order preferences.", exception)
                    emit(UserPreferences.getDefaultInstance())
                } else {
                    throw exception
                }
            }

    override suspend fun getLevel(): Level? {
        Log.d("UserPreferenceRepository", userPreferencesFlow.firstOrNull()?.level.toString())
        return if (userPreferencesFlow.firstOrNull()?.level == be.mbolle.wordytony.data.datastore.Level.INVALID) {
            null
        } else {
            userPreferencesFlow.firstOrNull()!!.level!!.toModel()
        }
    }

    override suspend fun setLevel(level: Level) {
        context.userPreferences.updateData { preferences ->
            preferences.toBuilder().setLevel(level.toData()).build()
        }
    }
}
