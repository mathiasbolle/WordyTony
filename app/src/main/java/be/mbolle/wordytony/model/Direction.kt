package be.mbolle.wordytony.model

import be.mbolle.wordytony.model.Direction.WidthHeight.value

sealed class Direction() {
    object Width : Direction()

    object Height : Direction()

    object WidthHeight : Direction() {
        var value: Direction
            private set

        init {
            value = generate()
        }

        /**
         * Regenerates the [value] property with a
         * [Direction.Width] or [Direction.Height].
         */
        fun regenerate() {
            value = generate()
        }

        private fun generate(): Direction {
            return setOf<Direction>(Width, Height).random()
        }
    }

    object Invalid : Direction() {
        // should throw an error or something
    }
}
