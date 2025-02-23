package be.mbolle.wordytony.ui.navigation

import be.mbolle.wordytony.model.Level
import kotlinx.serialization.Serializable

@Serializable
object MainScreen

@Serializable
data class PlayScreen(val level: Level)