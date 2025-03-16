package be.mbolle.wordytony.model

import be.mbolle.wordytony.model.Level.Easy
import be.mbolle.wordytony.model.Level.Hard
import be.mbolle.wordytony.model.Level.Medium

class Grid(level: Level) {
    var width: Int = 5
        private set
    var height: Int = 8
        private set
    private var secretWord: SecretWord

    // Maybe switch to anther data structure in the future?
    var tiles: Array<Array<Tile?>>
        private set
    var placedWord = mutableSetOf<Tile>()
        private set

    init {
        val multiplier =
            when (level) {
                Easy -> 1
                Medium -> 2
                Hard -> 3
            }
        width *= multiplier
        height *= multiplier
        tiles =
            Array(width) {
                Array(height) {
                    Tile(' ', x = null, y = null)
                }
            }
        secretWord = SecretWord(width, height)
        addWordToGrid()
        fillUpEmptyTiles()
    }

    /**
     * Perhaps name this to "determineIndex" that returns
     */
    private fun addWordToGrid() {
        val gridWord = secretWord.getPlaceInGrid()

        gridWord.forEach { tile ->
            println(tile)
            tiles[tile.x!! - 1][tile.y!! - 1] = tile
            placedWord.add(tile)
        }
    }

    private fun fillUpEmptyTiles() {
        tiles =
            tiles.map { element ->
                element.map { nextElement ->
                    if (nextElement?.content == ' ') {
                        Tile(
                            ('A'..'Z').random(),
                            x = nextElement.x,
                            y = nextElement.y,
                        )
                    } else {
                        nextElement
                    }
                }.toTypedArray()
            }.toTypedArray()
    }
}
