package be.mbolle.wordytony.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import be.mbolle.wordytony.R
import be.mbolle.wordytony.ui.theme.WordyTonyTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, playClick: () -> Unit, awardsClick: () -> Unit) {
    val defaultActionButtonOffset = Offset(x = 5.dp, y = 5.dp)

    Column(verticalArrangement = Arrangement.Bottom, modifier = modifier) {
        WordyTonyButton(
            modifier = Modifier.fillMaxWidth(), offset = defaultActionButtonOffset,
            onClick = playClick
        ) {
            Text(stringResource(R.string.play_btn))
        }
        WordyTonyButton(
            modifier = Modifier.fillMaxWidth(), offset = defaultActionButtonOffset,
            onClick = awardsClick
        ) {
            Text(stringResource(R.string.awards_btn))
        }
    }
}

data class Offset(val x: Dp, val y: Dp)

@Composable
fun WordyTonyButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    offset: Offset,
    internalPadding: PaddingValues = ButtonDefaults.ContentPadding,
    shape: Shape = MaterialTheme.shapes.large,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.padding(vertical = 10.dp, horizontal = 5.dp)) {
        Box(
            modifier = modifier
                .matchParentSize()
                .offset(x = offset.x, y = offset.y)
                .background(Color(0XFF3D887A), shape = shape)
                .then(modifier)
        )

        Button(
            onClick = { onClick() },
            modifier = modifier
                .height(IntrinsicSize.Min),
            shape = shape,
            enabled = enabled,
            contentPadding = internalPadding,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0XFF34B897),
                disabledContainerColor = Color(0XFF7FD0BC)
            )
        ) { // Breaks the Material UI guidelines <3
            content()
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
