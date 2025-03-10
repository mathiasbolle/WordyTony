package be.mbolle.wordytony.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.mbolle.wordytony.data.datastore.UserPreferencesRepository
import be.mbolle.wordytony.data.words
import be.mbolle.wordytony.model.Character
import be.mbolle.wordytony.model.Level
import kotlinx.coroutines.launch

class WordFinderViewModel(
    private var level: Level?,
    val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {
    private val multiplier =
        when (level) {
            Level.Easy -> 1
            Level.Medium -> 2
            Level.Hard -> 3
            null -> -1
        }

    val width: Int = 5 * multiplier
    val height: Int = 8 * multiplier
    var correctCharacters = mutableSetOf<Character>()

    var uiState by mutableStateOf(
        WordFinderState(
            characters =
                Array(width) {
                    Array(height) {
                        Character(' ', width = null, height = null)
                    }
                },
            randomWord = words.random(),
        ),
    )
        private set

    init {
        restoreLevelOfUserPreference()
        initSearchableWord(width, height)
    }

    fun restoreLevelOfUserPreference() {
        viewModelScope.launch {
            val cachedLevel = userPreferencesRepository.getLevel()
            level = cachedLevel
        }

        Log.d("WordFinderViewModel", "the state that is written to the pb file is $level")
    }

    fun registerKey(
        wIndex: Int,
        hIndex: Int,
    ) {
        // query the character
        val mutableCharacters = uiState.characters.toMutableList()
        mutableCharacters[wIndex][hIndex] =
            Character(
                mutableCharacters.get(wIndex).get(hIndex).content,
                !mutableCharacters.get(wIndex).get(hIndex).selected,
                width = mutableCharacters[wIndex][hIndex].width,
                height = mutableCharacters[wIndex][hIndex].height,
            )

        uiState =
            uiState.copy(
                characters = mutableCharacters.toTypedArray(),
                randomWord = uiState.randomWord,
            )

        // add to chosenCharacter
        // if it is correct to correctCharacters give toast message or smth?

        Log.d("WordFinderViewModel", correctCharacters.toString())
        Log.d(
            "WordfinderViewModel",
            uiState.characters.flatten().filter { it.selected }
                .toString(),
        )

        Log.d(
            "WordFinderViewModel",
            (
                uiState.characters
                    .flatten()
                    .filter { it.selected }
                    .toSet() == correctCharacters
            ).toString(),
        )

        if (uiState.characters.flatten().filter { it.selected }.toSet() == correctCharacters) {
            foundWord()
        }
    }

    fun foundWord() {
        uiState =
            uiState.copy(
                characters = uiState.characters,
                randomWord = uiState.randomWord,
                foundWord = true,
            )
    }

    // situation where variableIndex is equals to 1 so the filter will produce a
    fun chooseEndIndexInBoard(
        chosenWord: String,
        variableIndex: Int,
    ): Int {
        var validEndIndexes: Array<Int> = (0..variableIndex).toList().toTypedArray()

        validEndIndexes =
            validEndIndexes.filter { index ->
                index + 1 >= chosenWord.length
            }.toTypedArray()
        Log.d("x", validEndIndexes.toList().toString())

        return validEndIndexes.random()
    }

    /**
     * Places the generated word from in a index-based grid.
     * This grid is further generated by
     */
    fun initSearchableWord(
        width: Int,
        height: Int,
    ) {
        val chosenWord = uiState.randomWord
        Log.d("WordFinderViewModel", "The chosen word $chosenWord")

        val directionOfWord = getDirectionFromWord(chosenWord, width, height)
        Log.d("WordFinderViewModel", directionOfWord.name)

        val widthIndex = width - 1
        val heightIndex = height - 1

        when (directionOfWord) {
            Direction.WIDTH_HEIGHT -> {
                val constantHeight = (0..heightIndex).random()
                val constantWidth = (0..widthIndex).random()
                val randomDirection = setOf(Direction.WIDTH, Direction.HEIGHT).random()

                if (randomDirection == Direction.WIDTH) {
                    placeWordInWidth(chosenWord, widthIndex, constantHeight, randomDirection)
                    Log.d("WordFinderViewModel", "for the WIDTH direction, the constant height is $constantHeight")
                } else {
                    placeWordInHeight(chosenWord, constantWidth, heightIndex, randomDirection)
                    Log.d("WordFinderViewModel", "for the HEIGHT direction, the constant width is $constantWidth")
                }
            }

            Direction.WIDTH -> {
                val constantHeight = (0..heightIndex).random()
                placeWordInWidth(chosenWord, widthIndex, constantHeight, directionOfWord)
            }

            Direction.HEIGHT -> {
                val constantWidth = (0..widthIndex).random()
                placeWordInHeight(chosenWord, constantWidth, heightIndex, directionOfWord)

                Log.d("WordFinderViewModel", "for the HEIGHT direction, the constant height is $constantWidth")
            }

            Direction.INVALID -> {
                // TODO handle some error state right here!
            }
        }
        uiState =
            uiState.copy(
                characters = fillUpEmptySpots(uiState.characters),
                randomWord = uiState.randomWord,
            )
    }

    fun placeWordInWidth(
        word: String,
        widthIndex: Int,
        constantHeight: Int,
        directionOfWord: Direction,
    ) {
        setWordOnGrid(word, widthIndex, constantHeight, directionOfWord)
    }

    fun placeWordInHeight(
        word: String,
        constantWidth: Int,
        heightIndex: Int,
        directionOfWord: Direction,
    ) {
        setWordOnGrid(word, heightIndex, constantWidth, directionOfWord)
    }

    private fun setWordOnGrid(
        word: String,
        variableIndex: Int,
        constantIndex: Int,
        direction: Direction,
    ) {
        val endIndexOfWord = chooseEndIndexInBoard(word, variableIndex)
        val widthIndexes = indexesOf(word, endIndexOfWord)

        val constantHeight = (0..constantIndex).random()

        if (direction == Direction.WIDTH) {
            widthIndexes.forEachIndexed { index, element ->
                uiState.characters[element][constantHeight] =
                    Character(word[index], width = element, height = constantHeight)
                correctCharacters.add(
                    Character(
                        word[index],
                        selected = true,
                        width = element,
                        height = constantHeight,
                    ),
                )
                // terrain[element][constantHeight] = word[index]
            }
        } else {
            widthIndexes.forEachIndexed { index, element ->
                uiState.characters[constantHeight][element] =
                    Character(word[index], width = element, height = constantHeight)
                correctCharacters.add(
                    Character(
                        word[index],
                        selected = true,
                        width = element,
                        height = constantHeight,
                    ),
                )
                // terrain[constantHeight][element] = word[index]
            }
        }
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

    fun fillUpEmptySpots(terrain: Array<Array<Character>>): Array<Array<Character>> {
        return terrain.map { element ->
            element.map { nextElement ->
                if (nextElement.content == ' ') {
                    Character(
                        ('A'..'Z').random(),
                        width = nextElement.width,
                        height = nextElement.height,
                    )
                } else {
                    nextElement
                }
            }.toTypedArray()
        }.toTypedArray()
    }

    private fun getDirectionFromWord(
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
}

/**
 * The possible directions that a word can be placed on the screen.
 */
enum class Direction {
    WIDTH,
    HEIGHT,
    WIDTH_HEIGHT,
    INVALID,
}
