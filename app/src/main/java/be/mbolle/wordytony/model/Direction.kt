package be.mbolle.wordytony.model

enum class Direction {
    WIDTH,
    HEIGHT,
    WIDTH_HEIGHT,
    INVALID,
}

fun Direction.directionOfWord(
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
