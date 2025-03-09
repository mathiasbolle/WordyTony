package be.mbolle.wordytony.model

import android.service.quicksettings.Tile
import be.mbolle.wordytony.data.words

/**
 * Chose a random word from the [words] collection.
 */
class WordFinderGame(level: Level) {
    lateinit var chosenWord: String
        private set
    private var usedWords: MutableSet<String> = mutableSetOf()
    private var grid: Grid

    init {
        choseRandomWord()

        grid = Grid(level = level, word = chosenWord)
    }

    private fun choseRandomWord() {
        var generatedWord = words.random()
        while (usedWords.contains(generatedWord)) {
            generatedWord = words.random()
        }

        chosenWord = generatedWord
        usedWords.add(chosenWord)
    }

    fun selectTile(tile: Tile) {
    }
}
