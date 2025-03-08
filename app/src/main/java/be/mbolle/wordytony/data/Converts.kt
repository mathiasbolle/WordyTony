package be.mbolle.wordytony.data

import android.util.Log
import be.mbolle.wordytony.data.datastore.Level as DataLevel
import be.mbolle.wordytony.model.Level as ModelLevel

fun DataLevel.toModel(): ModelLevel {
    Log.d("Converts", "${this.name} from the toModel() method")
    return when (this) {
        DataLevel.EASY -> ModelLevel.Easy
        DataLevel.MEDIUM -> ModelLevel.Medium
        DataLevel.HARD -> ModelLevel.Hard
        else -> throw IllegalStateException("not a right conversion.")
    }
}

fun ModelLevel.toData(): DataLevel {
    return when (this) {
        ModelLevel.Easy -> DataLevel.EASY
        ModelLevel.Medium -> DataLevel.MEDIUM
        ModelLevel.Hard -> DataLevel.HARD
    }
}
