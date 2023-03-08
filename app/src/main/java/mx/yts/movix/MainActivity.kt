package mx.yts.movix

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mxalbert.sharedelements.SharedElementsRoot
import dagger.hilt.android.AndroidEntryPoint
import hosein.haqiqian.player.Player
import hosein.haqiqian.player.PlayerParams
import mx.yts.musiclist.ui.Musics
import mx.yts.musiclist.ui.MusicsParams

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold {
                    Main(rememberNavController())
                }
            }
        }
    }
}

@Composable
fun Main(navController: NavHostController) {
    SharedElementsRoot {
    NavHost(navController, startDestination = MusicsParams.route) {
        composable(MusicsParams.route) {
            Musics(
                viewModel = hiltViewModel(),
                openPlayer = { id ->
                    navController.navigate(PlayerParams.routeWithParam(id))
                },
            )
        }
        composable(
            PlayerParams.route,
            arguments = listOf(
                navArgument(PlayerParams.paramKey) { type = NavType.StringType },
            ),

            ) { navBackStackEntry ->

            Player(
                viewModel = hiltViewModel(),
                navBackStackEntry.arguments?.getString(PlayerParams.paramKey)
            )
        }
    }
    }
}