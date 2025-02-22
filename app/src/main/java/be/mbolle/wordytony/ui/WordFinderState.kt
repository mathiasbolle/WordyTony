package be.mbolle.wordytony.ui

import be.mbolle.wordytony.model.Character

data class WordFinderState(
    val characters: Array<Array<Character>>,
    val randomWord: String,
    val foundWord: Boolean = false,
)