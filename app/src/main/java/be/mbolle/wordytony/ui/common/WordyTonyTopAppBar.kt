package be.mbolle.wordytony.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.mbolle.wordytony.R

@Composable
fun WordyTonyTopAppBar(modifier: Modifier = Modifier, title: String, rightItem: (@Composable () -> Unit)? = null) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top, modifier = modifier) {
        Column {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Text(stringResource(R.string.subtitle), style = MaterialTheme.typography.titleMedium)
        }
        rightItem?.let { it() }
    }
}