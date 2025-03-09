package be.mbolle.wordytony.model

/**
 * Represents a character tile in the game.
 */
data class Tile(
    val content: Char,
    val selected: Boolean = false,
    val x: Int?,
    val y: Int?,
) {
    override fun toString(): String {
        return "${this.content} ${this.x} ${this.y}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tile

        if (content != other.content) return false
        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = content.hashCode()
        result = 31 * result + (x ?: 0)
        result = 31 * result + (y ?: 0)
        return result
    }
}
