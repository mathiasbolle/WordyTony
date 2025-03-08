package be.mbolle.wordytony.model

/**
 * Represents a character in the game.
 */
data class Character(
    val content: Char,
    val selected: Boolean = false,
    val width: Int?,
    val height: Int?,
) {
    override fun toString(): String {
        return "${this.content} ${this.width} ${this.height}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (content != other.content) return false
        if (width != other.width) return false
        if (height != other.height) return false

        return true
    }

    override fun hashCode(): Int {
        var result = content.hashCode()
        result = 31 * result + (width ?: 0)
        result = 31 * result + (height ?: 0)
        return result
    }
}
