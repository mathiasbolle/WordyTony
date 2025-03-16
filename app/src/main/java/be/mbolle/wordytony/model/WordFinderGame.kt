package be.mbolle.wordytony.model

import android.service.quicksettings.Tile
import be.mbolle.wordytony.data.words

/**
 * Chose a random word from the [words] collection.
 */
class WordFinderGame(level: Level) {
    private var usedWords: MutableSet<Tile> = mutableSetOf()
    var grid: Grid
        private set

    init {
        grid = Grid(level = level)
    }

    /**
     * the only action that a player can do.
     */
    fun selectTile(tile: Tile) {
        usedWords.add(tile)
    }

    fun hasWon(): Boolean {
        return usedWords == grid.placedWord
    }
}
