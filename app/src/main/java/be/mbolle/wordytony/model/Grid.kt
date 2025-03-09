package be.mbolle.wordytony.model

import android.util.Log
import be.mbolle.wordytony.model.Level.Easy
import be.mbolle.wordytony.model.Level.Hard
import be.mbolle.wordytony.model.Level.Medium

class Grid(level: Level, val word: String) {
    private var width: Int = 5
    private var height: Int = 8

    private lateinit var tiles: Array<Array<Tile?>>
    private var placedWord = mutableSetOf<Tile>()

    init {
        val multiplier =
            when (level) {
                Easy -> 1
                Medium -> 2
                Hard -> 3
            }
        width *= multiplier
        height *= multiplier

        addWordToGrid()
        fillUpEmptyTiles()
    }

    private fun addWordToGrid() {
        val wordDirection =
            getDirectionOfWord(
                word = word,
                width = width,
                height = height,
            )

        val widthIndex = width - 1
        val heightIndex = height - 1

        when (wordDirection) {
            Direction.WIDTH_HEIGHT -> {
                val constantHeight = (0..heightIndex).random()
                val constantWidth = (0..widthIndex).random()
                val randomDirection = setOf(Direction.WIDTH, Direction.HEIGHT).random()

                if (randomDirection == Direction.WIDTH) {
                    placeWord(widthIndex, constantHeight)
                } else {
                    placeWord(heightIndex, constantWidth)
                }
            }

            Direction.WIDTH -> {
                val constantHeight = (0..heightIndex).random()
                placeWord(widthIndex, constantHeight)
            }

            Direction.HEIGHT -> {
                val constantWidth = (0..widthIndex).random()
                placeWord(heightIndex, constantWidth)
            }

            Direction.INVALID -> {
                // TODO handle some error state right here!
            }
        }
    }

    /**
     * Perhaps name this to "determineIndex" that returns
     */
    private fun placeWord(
        widthIndex: Int,
        constantHeight: Int,
    ) {
        val endIndexOfWord = chooseEndIndexInBoard(widthIndex)
        val widthIndexes = indexesOf(word, endIndexOfWord)

        widthIndexes.forEachIndexed { index, element ->
            tiles[element][constantHeight] =
                Tile(word[index], x = element, y = constantHeight)

            placedWord.add(
                Tile(
                    word[index],
                    selected = true,
                    x = element,
                    y = constantHeight,
                ),
            )
        }
    }

    private fun chooseEndIndexInBoard(variableIndex: Int): Int {
        var validEndIndexes: Array<Int> = (0..variableIndex).toList().toTypedArray()

        validEndIndexes =
            validEndIndexes.filter { index ->
                index + 1 >= word.length
            }.toTypedArray()
        Log.d("x", validEndIndexes.toList().toString())

        return validEndIndexes.random()
    }

    private fun indexesOf(
        word: String,
        endIndex: Int,
    ): Array<Int> {
        var x = endIndex
        val output: Array<Int> = IntArray(word.length).toTypedArray()

        word.forEachIndexed { index, element ->
            output[index] = x
            x--
        }

        return output.reversedArray()
    }

    private fun getDirectionOfWord(
        word: String,
        width: Int,
        height: Int,
    ): Direction {
        if (word.length > width && word.length > height) {
            return Direction.INVALID
        }
        if (word.length <= width) {
            if (word.length <= height) {
                return Direction.WIDTH_HEIGHT
            }
            return Direction.WIDTH
        }
        return Direction.HEIGHT
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
