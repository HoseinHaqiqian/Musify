package mx.yts.musiclist.ui

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mxalbert.sharedelements.FadeMode
import com.mxalbert.sharedelements.SharedElement
import com.mxalbert.sharedelements.SharedElementsTransitionSpec
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.StateFlow
import mx.yts.common.utils.RequestPermissions
import mx.yts.common.utils.Result
import mx.yts.common.utils.loadImage
import mx.yts.domain.model.MusicModel
import mx.yts.musiclist.viewmodels.MusicListViewModel

class MusicsParams {
    companion object {
        const val route = "music_list"
    }
}

@Composable
fun Musics(viewModel: MusicListViewModel, openPlayer: (id: String) -> Unit) {
    RequestPermissions(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) {
        viewModel.getMusics()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Music List")
                },
                backgroundColor = Color.Transparent,
                elevation = 1.dp,
            )
        },
    ) {
        MusicList(viewModel.musicUiState, openPlayer)
    }
}

@Composable
fun MusicList(musicUiState: StateFlow<Result<List<MusicModel>>>, openPlayer: (id: String) -> Unit) {
    val godState by remember { mutableStateOf(musicUiState) }
    Column {
        when (val state = godState.collectAsState().value) {
            is Result.Failure -> NoMusicView()
            is Result.Loading -> Text("Loading...")
            is Result.Success -> ListOfMusics(state.data, openPlayer)
        }
    }
}

@Composable
fun NoMusicView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(modifier = Modifier.size(200.dp, 200.dp)) {
            NoMusicResult()
        }
        Text(text = "No Music found")
    }
}

@Composable
fun ListOfMusics(data: List<MusicModel>, openPlayer: (id: String) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(data.size) { index ->
            val item = data[index]
            MusicCard(item, openPlayer)
        }
    }
}

@Composable
fun MusicCard(item: MusicModel, openPlayer: (id: String) -> Unit) {

    Card(elevation = 5.dp, contentColor = Color.Transparent, backgroundColor = Color.Transparent) {
        Box(modifier = Modifier.clickable { openPlayer.invoke(item.id.toString()) }) {
            SharedElement(
                key = "picture - ${item.id}", screenKey = "picture1",
                transitionSpec = SharedElementsTransitionSpec(fadeMode = FadeMode.Out),
            ) {
                GlideImage(
                    imageModel = loadImage(item.artUrl, LocalContext.current),
                    Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp, 5.dp, 0.dp, 0.dp))
                        .shadow(4.dp, RectangleShape)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xDA7A7A7),
                                Color(0x80747474),
                                Color(0xFF444343),
                            ),
                        ),
                    ),

                ) {
                Text(
                    item.name,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}


