package be.mbolle.wordytony.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WordFinderScreen(modifier: Modifier = Modifier,
                     viewModel: WordFinderViewModel = viewModel()) {
    val defaultPadding = 15.dp
    val width = 5
    val height = 8



    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        repeat(width) { widthIndex ->
            if (widthIndex == width) {
                PlayBoardRow(
                    height = height,
                    defaultPadding = defaultPadding) {
                }
            } else {
                PlayBoardRow(modifier = Modifier.padding(bottom = defaultPadding),
                    height = height,
                    defaultPadding = defaultPadding) {
                }
            }
        }
    }
}

@Composable
fun PlayBoardRow(modifier: Modifier = Modifier,
                 height: Int,
                 defaultPadding: Dp,
                 onClick: (letter: String) -> Unit) {
    Row(modifier = modifier) {
        repeat(times = height) { heightIndex ->
            LetterItem(modifier = Modifier.weight(1f), onClick = {e -> onClick(e)})
            Spacer(modifier = Modifier.width(defaultPadding))

            if (heightIndex == height) {
                LetterItem(modifier = Modifier.weight(1f), onClick = { e -> onClick(e) })
                Spacer(modifier = Modifier.width(defaultPadding))
            } else {
                LetterItem(modifier = Modifier.weight(1f), onClick = {e -> onClick(e)})
            }
        }
    }
}

@Composable
fun LetterItem(letter: String = "E",
               onClick: (letter: String) -> Unit,
               modifier: Modifier = Modifier) {
    Box(modifier = modifier.clickable { onClick(letter) }) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .background(Color(0XFFD9D9D9)).aspectRatio(1f)) {
            Text(text = letter, fontSize = 20.sp)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewWordFinder() {
    WordFinderScreen()
}