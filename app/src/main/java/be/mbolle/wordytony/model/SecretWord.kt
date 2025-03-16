package be.mbolle.wordytony.model

import be.mbolle.wordytony.data.words

/**
 * Represents a word in a Grid.
 */
class SecretWord(private val width: Int, private val height: Int) {
    lateinit var chosenWord: String
        private set
    private var usedWords: MutableSet<String> = mutableSetOf()

    init {
        choseRandomWord()
    }

    fun choseRandomWord() {
        var generatedWord = words.random()
        while (usedWords.contains(generatedWord)) {
            if (usedWords.size == words.size) {
                throw IllegalStateException("there are no words left.")
            }
            generatedWord = words.random()
        }

        chosenWord = generatedWord
        usedWords.add(chosenWord)
    }

    private fun getDirectionOfWord(): Direction {
        if (chosenWord.length > width && chosenWord.length > height) {
            return Direction.Invalid
        }
        if (chosenWord.length <= width) {
            if (chosenWord.length <= height) {
                return Direction.WidthHeight.value
            }
            return Direction.Width
        }
        return Direction.Height
    }

    /**
     * The last index where it is possible for the word to be set.
     */
    private fun getEndIndex(): Int {
        val variableIndex =
            if (getDirectionOfWord() == Direction.Width) height - 1 else width - 1
        var validEndIndexes = (0..variableIndex).toList().toTypedArray()

        validEndIndexes =
            validEndIndexes.filter { index ->
                index >= chosenWord.length - 1
            }.toTypedArray()

        return validEndIndexes.random()
    }

    private fun getAllIndexes(): Array<Int> {
        var endIndex = getEndIndex()
        val indexes = IntArray(chosenWord.length).toTypedArray()

        chosenWord.forEachIndexed { index, element ->
            indexes[index] = endIndex
            endIndex--
        }

        return indexes.reversedArray()
    }

    fun getPlaceInGrid(): Set<Tile> { // should return a array of array of Tiles?
        // constant width or height
        val constantIndex =
            if (getDirectionOfWord() == Direction.Width) {
                (0..<height).toList()
                    .random()
            } else {
                (0..<width).toList().random()
            }

        return getAllIndexes().mapIndexed { index, element ->
            Tile(
                chosenWord[index],
                selected = false,
                x = if (getDirectionOfWord() == Direction.Width) element else constantIndex,
                y = if (getDirectionOfWord() == Direction.Width) constantIndex else element,
            )
        }.toMutableSet()
    }
}
