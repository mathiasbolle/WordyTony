package be.mbolle.wordytony.ui

import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import be.mbolle.wordytony.R
import be.mbolle.wordytony.data.datastore.DatastoreUserPreferencesRepository
import be.mbolle.wordytony.ui.common.Offset
import be.mbolle.wordytony.ui.common.WordyTonyButton
import be.mbolle.wordytony.ui.common.WordyTonyTopAppBar
import be.mbolle.wordytony.ui.navigation.MainScreen
import be.mbolle.wordytony.ui.navigation.PlayScreen
import be.mbolle.wordytony.ui.screen.HomeScreen
import be.mbolle.wordytony.ui.screen.HomeScreenViewModel
import be.mbolle.wordytony.ui.screen.HomeScreenViewModelFactory
import be.mbolle.wordytony.ui.screen.WordFinderScreen
import be.mbolle.wordytony.ui.screen.WordFinderViewModel
import be.mbolle.wordytony.ui.screen.WordFinderViewModelFactory
import be.mbolle.wordytony.ui.theme.WordyTonyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    val scaffoldModifier =
        Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 25.dp)

    val repo =
        DatastoreUserPreferencesRepository(context = LocalContext.current)

    val homeScreenViewmodel =
        viewModel(
            HomeScreenViewModel::class,
            factory = HomeScreenViewModelFactory(repository = repo),
        )

    NavHost(navController = navController, startDestination = MainScreen) {
        composable<MainScreen> {
            Scaffold(
                modifier = scaffoldModifier,
                topBar = {
                    WordyTonyTopAppBar(
                        title = stringResource(R.string.title),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        WordyTonyButton(
                            onClick = {},
                            enabled = false,
                            offset = Offset(4.dp, 4.dp),
                            shape = CircleShape,
                            internalPadding = PaddingValues(0.dp),
                            modifier =
                                Modifier
                                    .width(70.dp)
                                    .aspectRatio(1f)
                                    .fillMaxWidth(),
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = stringResource(R.string.img_settings),
                                modifier = Modifier,
                            )
                        }
                    }
                },
            ) { innerPadding ->
                HomeScreen(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    playMenuClick = { level ->

                        navController.navigate(PlayScreen(level = level))
                    },
                    awardsClick = {
                    },
                    viewModel = homeScreenViewmodel,
                )
            }
        }

        composable<PlayScreen> {
            val args = it.toRoute<PlayScreen>()

            Scaffold(
                modifier = scaffoldModifier,
                topBar = {
                    WordyTonyTopAppBar(
                        title = stringResource(R.string.minigame),
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
            ) { innerPadding ->
                WordFinderScreen(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    viewModel =
                        viewModel(
                            WordFinderViewModel::class,
                            factory =
                                WordFinderViewModelFactory(
                                    repository = repo,
                                    level = args.level,
                                ),
                        ),
                    onFinishGame = {
                        val scope = rememberCoroutineScope()
                        LaunchedEffect(key1 = null) {
                            scope.launch {
                                launch {
                                    delay(1000)
                                    navController.navigate(MainScreen)
                                }
                            }
                        }
                    },
                )
            }
        }
    }
}
