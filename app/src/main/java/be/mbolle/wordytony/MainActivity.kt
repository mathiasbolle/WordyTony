package be.mbolle.wordytony

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import be.mbolle.wordytony.ui.App
import be.mbolle.wordytony.ui.theme.WordyTonyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordyTonyTheme {
                App()
            }
        }
    }
}
