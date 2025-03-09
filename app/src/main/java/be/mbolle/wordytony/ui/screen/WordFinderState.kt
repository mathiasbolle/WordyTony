package be.mbolle.wordytony.ui.screen

import be.mbolle.wordytony.model.Tile

data class WordFinderState(
    val tiles: Array<Array<Tile>>,
    val randomWord: String,
    val foundWord: Boolean = false,
)
