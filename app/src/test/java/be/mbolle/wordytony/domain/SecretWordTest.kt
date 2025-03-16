package be.mbolle.wordytony.domain

import be.mbolle.wordytony.data.words
import be.mbolle.wordytony.model.SecretWord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

class SecretWordTest {
    @Test
    fun `GIVEN - WHEN random word is chosen for more then the amount of word left THEN an exception is thrown`() {
        var secretWord = SecretWord(5, 8)
        assertThrows(
            IllegalStateException::class.java,
            Executable {
                repeat(words.size) {
                    secretWord.choseRandomWord()
                }
            },
        )
    }

    @Test
    fun `GIVEN a Grid WHEN a word from the list is chosen THEN the grid is placed`() {
        val secretWord = SecretWord(5, 8)

        val actualPlacedWord: Set<Char> =
            secretWord.getPlaceInGrid()
                .map { tile -> tile.content }
                .toSet()
        val expectedPlacedWord: Set<Char> = setOf('E', 'M', 'E', 'L', 'Y')

        assertEquals(expectedPlacedWord, actualPlacedWord)
    }
}
