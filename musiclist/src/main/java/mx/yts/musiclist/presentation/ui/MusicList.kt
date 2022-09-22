package mx.yts.musiclist.presentation.ui

import android.graphics.BitmapFactory
import android.util.Size
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.StateFlow
import mx.yts.common.RequestPermission
import mx.yts.common.RequestPermissions
import mx.yts.common.Result
import mx.yts.domain.model.MusicModel
import mx.yts.musiclist.R
import mx.yts.musiclist.presentation.viewmodels.MusicListViewModel
import java.io.File

class MusicsParams {
    companion object {
        const val route = "/music_list"
    }
}

@Composable
fun Musics(viewModel: MusicListViewModel) {
    RequestPermissions(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) {
        viewModel.getMusics()
    }
    Surface() {
        MusicList(viewModel.musicUiState)
    }
}

@Composable
fun MusicList(musicUiState: StateFlow<Result<List<MusicModel>>>) {

    Column {
        val state = musicUiState.collectAsState().value
        when (state) {
            is Result.Failure -> Text("Failure")
            Result.Loading -> Text("Loading...")
            is Result.Success -> ListOfMusics(state.data)
        }
    }
}

@Composable
fun ListOfMusics(data: List<MusicModel>) {

    LazyColumn() {
        items(data) { item ->
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 12.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    GlideImage(
                        imageModel = LocalContext.current.contentResolver.loadThumbnail(
                            item.artPat,
                            Size(50, 50), null
                        ),
                        Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(item.name ?: "", Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_media_play),
                        contentDescription = null // decorative element

                    )
                }
                Divider(thickness = 1.dp, color = Color.LightGray)
            }
        }
    }
}

