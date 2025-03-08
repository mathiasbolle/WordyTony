package be.mbolle.wordytony.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import be.mbolle.wordytony.R

private val pushPinsBold =
    FontFamily(
        Font(R.font.pushpins_light),
        Font(R.font.pushpins_bold),
    )

private val pushPinsLight =
    FontFamily(
        Font(R.font.pushpins_light),
    )

// Set of Material typography styles to start with
val Typography =
    Typography(
        bodyLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
            ),
        titleLarge =
            TextStyle(
                fontFamily = pushPinsBold,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                letterSpacing = 0.sp,
                lineHeight = 0.sp,
            ),
        titleMedium =
            TextStyle(
                fontFamily = pushPinsLight,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
            ),
        labelLarge =
            TextStyle(
                fontFamily = pushPinsLight,
                fontWeight = FontWeight.Normal,
                fontSize = 25.sp,
                letterSpacing = 0.sp,
            ),
    /* Other default text styles to override


    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
     */
    )
