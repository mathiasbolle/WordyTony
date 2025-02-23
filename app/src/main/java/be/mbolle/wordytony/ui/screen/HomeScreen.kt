package be.mbolle.wordytony.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.mbolle.wordytony.R
import be.mbolle.wordytony.model.Level
import be.mbolle.wordytony.model.Level.Easy
import be.mbolle.wordytony.model.Level.Hard
import be.mbolle.wordytony.model.Level.Medium
import be.mbolle.wordytony.ui.common.Offset
import be.mbolle.wordytony.ui.common.WordyTonyButton
import be.mbolle.wordytony.ui.theme.WordyTonyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    playMenuClick: (level: Level) -> Unit,
    awardsClick: () -> Unit,
    viewModel: HomeScreenViewModel
) {
    val bottomSheetState = viewModel.showBottomSheet
    val defaultActionButtonOffset = Offset(x = 5.dp, y = 5.dp)

    if (bottomSheetState) {
        GameModeBottomSheet(modifier =
            Modifier.fillMaxWidth(),

            disable = {
                viewModel.hideBottomSheet() },
            onPlay = { playMenuClick(it) })
    }

    Column(verticalArrangement = Arrangement.Bottom, modifier = modifier) {
        WordyTonyButton(
            modifier = Modifier.fillMaxWidth(), offset = defaultActionButtonOffset,
            onClick = {
                viewModel.showBottomSheet()
            }
        ) {
            Text(stringResource(R.string.play_btn))
        }
        WordyTonyButton(
            modifier = Modifier.fillMaxWidth(), offset = defaultActionButtonOffset,
            onClick = awardsClick,
            enabled = false,
        ) {
            Text(stringResource(R.string.awards_btn))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun WordyTonyButtonPreview() {
    WordyTonyTheme {
        WordyTonyButton(offset = Offset(x = 5.dp, y = 5.dp), onClick = {}) {
            Text("This is a sample text")
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun GameModeBottomSheet(
    modifier: Modifier = Modifier, disable: () -> Unit,
    onPlay: (level: Level) -> Unit
) {
    ModalBottomSheet(onDismissRequest = { disable() }, modifier = modifier) {
        Text(text = "LEVEL", style = MaterialTheme.typography.titleLarge, modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            textAlign = TextAlign.Center)
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            LevelButton(level = Easy, onPlay = {level -> onPlay(level)})
            LevelButton(level = Medium, onPlay = {level -> onPlay(level)})
            LevelButton(level = Hard, onPlay = {level -> onPlay(level)})
        }
    }
}

@Composable
fun LevelButton(modifier: Modifier = Modifier, level: Level, onPlay: (level: Level) -> Unit) {
    WordyTonyButton(
        onClick = {
            onPlay(level)
        },
        offset = Offset(4.dp, 4.dp),
        internalPadding = PaddingValues(horizontal = 15.dp),
        modifier = modifier
    ) {
        when (level) {
            Easy -> {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "star"
                )
                Icon(
                    imageVector = Icons.TwoTone.Star,
                    contentDescription = "star"
                )
                Icon(
                    imageVector = Icons.TwoTone.Star,
                    contentDescription = "star"
                )
            }
            Medium -> {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "star"
                )
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "star"
                )
                Icon(
                    imageVector = Icons.TwoTone.Star,
                    contentDescription = "star"
                )
            }
            Hard -> {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "star"
                )
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "star"
                )
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "star"
                )
            }
        }
    }
}