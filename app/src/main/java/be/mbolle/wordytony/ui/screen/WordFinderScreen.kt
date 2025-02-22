package be.mbolle.wordytony.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.mbolle.wordytony.model.Character

@Composable
fun WordFinderScreen(
    modifier: Modifier = Modifier,
    viewModel: WordFinderViewModel = viewModel(),
    onFinishGame: @Composable () -> Unit
) {
    val width = 5
    val height = 8

    Log.d("WordFinderScreen", viewModel.uiState.toString())

    Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxHeight()) {
        if (viewModel.uiState.foundWord) {
            Text(text = "${viewModel.uiState.randomWord} Found 🎉!!", color = Color(0XFF4DE5BF),
                fontSize = 25.sp)
            onFinishGame()

        }
        WordGrid(
            width = width,
            height = height,
            characters = viewModel.uiState.characters
        ) { widthIndex, heightIndex ->
            viewModel.registerKey(widthIndex, heightIndex)
        }
    }
}

@Composable
fun WordGrid(
    modifier: Modifier = Modifier,
    width: Int,
    height: Int,
    characters: Array<Array<Character>>, //replace Char to Character
    registerCharacter: (widthIndex: Int, heightIndex: Int) -> Unit
) {

    Column(modifier = modifier) {
        repeat(height) { heightIndex ->
            val bottomPadding = if (heightIndex+1 == height) 0.dp else 5.dp
            Row(modifier = Modifier.padding(bottom = bottomPadding)) {
                repeat(width) { widthIndex ->
                    val rightPadding = if (widthIndex+1 == width) 0.dp else 5.dp


                    var defaultColor = Color(0XFFD9D9D9)
                    if (characters[widthIndex][heightIndex].selected) {
                        defaultColor = Color(0XFF4DE5BF)
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(end = rightPadding)
                            .background(defaultColor)
                            .clickable {
                                registerCharacter(widthIndex, heightIndex)
                            }, contentAlignment = Alignment.Center
                    ) {


                        Text("${characters[widthIndex][heightIndex].content}") // characters width height
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WordGridPreview() {
    val width = 5
    val height = 8
    val viewModel: WordFinderViewModel = viewModel()

    WordGrid(
        width = width,
        height = height,
        characters = viewModel.uiState.characters
    ) { widthIndex, heightIndex ->
        Log.d("WordFinderScreen", "hehe")
    }
}