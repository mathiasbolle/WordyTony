package be.mbolle.wordytony.domain

import be.mbolle.wordytony.model.Level
import be.mbolle.wordytony.model.WordFinderGame
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class GridTest {
    @ParameterizedTest
    @EnumSource(Level::class, names = ["Hard"])
    fun `GIVEN a level WHEN level is set to hard THEN the length of the grid is 3 times as big`(level: Level) {
        var wordGame = WordFinderGame(level)
        val grid = wordGame.grid

        val defaultWidth = 5
        val defaultHeight = 8
        val actualSizeOfGrid = Pair(grid.width, grid.height)
        val expectedSizeOfGrid = Pair(defaultWidth * 3, defaultHeight * 3)

        Assertions.assertEquals(
            expectedSizeOfGrid,
            actualSizeOfGrid,
            "The width and/or height of the $grid class are not adapted for the ${Level.Hard} level.",
        )
    }

    @ParameterizedTest
    @EnumSource(Level::class, names = ["Easy"])
    fun `GIVEN a level WHEN level is set to easy THEN the length of the grid is set to it's normal size`(level: Level) {
        var wordGame = WordFinderGame(level)
        val grid = wordGame.grid

        val defaultWidth = 5
        val defaultHeight = 8
        val actualSizeOfGrid = Pair(grid.width, grid.height)
        val expectedSizeOfGrid = Pair(defaultWidth, defaultHeight)

        Assertions.assertEquals(
            expectedSizeOfGrid,
            actualSizeOfGrid,
            "The width and/or height of the $grid class are not adapted for the ${Level.Easy} level.",
        )
    }
}
