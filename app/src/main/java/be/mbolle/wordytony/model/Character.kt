package be.mbolle.wordytony.model

/**
 * Represents a character in the game.
 */
data class Character(
    val content: Char,
    val selected: Boolean = false
)