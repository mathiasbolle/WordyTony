package be.mbolle.wordytony

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import be.mbolle.wordytony.ui.HomeScreen
import be.mbolle.wordytony.ui.Offset
import be.mbolle.wordytony.ui.WordFinderScreen
import be.mbolle.wordytony.ui.WordyTonyButton
import be.mbolle.wordytony.ui.navigation.MainScreen
import be.mbolle.wordytony.ui.navigation.PlayScreen
import be.mbolle.wordytony.ui.theme.WordyTonyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

@Preview
@Composable
fun App() {
    val navController = rememberNavController()
    WordyTonyTheme {
        NavigationHandler(navController)
    }
}

@Composable
fun NavigationHandler(navController: NavHostController) {
    val scaffoldModifier = Modifier
        .fillMaxSize()
        .safeDrawingPadding()
        .padding(horizontal = 25.dp)

    NavHost(navController = navController, startDestination = MainScreen) {
        composable<MainScreen> {
            Scaffold(modifier = scaffoldModifier,
                topBar = {
                    WordyTonyTopAppBar(
                        title = stringResource(R.string.title),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        WordyTonyButton(
                            onClick = {},
                            offset = Offset(4.dp, 4.dp),
                            shape = CircleShape,
                            modifier = Modifier.width(70.dp).aspectRatio(1f).fillMaxWidth()
                        ) {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings icon", modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            ) { innerPadding ->
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    playClick = { navController.navigate(PlayScreen) },
                    awardsClick = {}
                )
            }
        }

        composable<PlayScreen> {
            Scaffold(modifier = scaffoldModifier,
                topBar = {
                    WordyTonyTopAppBar(
                        title = "WORD FINDER",
                        modifier = Modifier.fillMaxWidth()
                    ) { } //TODO fix!!
                }
            ) { innerPadding ->
                WordFinderScreen(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                    onFinishGame =  {
                        val scope = rememberCoroutineScope()
                        LaunchedEffect(key1 = null) {
                            scope.launch {
                                launch {
                                    delay(1000)
                                    navController.navigate(MainScreen)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun WordyTonyTopAppBar(modifier: Modifier = Modifier, title: String, rightItem: (@Composable () -> Unit)) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top, modifier = modifier) {
        Column {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Text(stringResource(R.string.subtitle), style = MaterialTheme.typography.titleMedium)
        }
        rightItem()
    }
}