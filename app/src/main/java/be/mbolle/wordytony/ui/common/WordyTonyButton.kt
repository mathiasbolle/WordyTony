package be.mbolle.wordytony.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Offset(val x: Dp, val y: Dp)

@Composable
fun WordyTonyButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    offset: Offset,
    internalPadding: PaddingValues = ButtonDefaults.ContentPadding,
    shape: Shape = MaterialTheme.shapes.large,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier.padding(vertical = 10.dp, horizontal = 5.dp)) {
        Box(
            modifier =
                modifier
                    .matchParentSize()
                    .offset(x = offset.x, y = offset.y)
                    .background(Color(0XFF3D887A), shape = shape),
        )

        Button(
            onClick = { onClick() },
            modifier =
                modifier
                    .height(IntrinsicSize.Min),
            shape = shape,
            enabled = enabled,
            contentPadding = internalPadding,
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color(0XFF34B897),
                    disabledContainerColor = Color(0XFF7FD0BC),
                ),
        ) { // Breaks the Material UI guidelines <3
            content()
        }
    }
}
