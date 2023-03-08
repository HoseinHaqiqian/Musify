package hosein.haqiqian.player

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mxalbert.sharedelements.FadeMode
import com.mxalbert.sharedelements.SharedElement
import com.mxalbert.sharedelements.SharedElementsTransitionSpec
import com.skydoves.landscapist.glide.GlideImage
import hosein.haqiqian.player.viewmodels.PlayerViewModel
import mx.yts.common.utils.Result
import mx.yts.common.utils.loadImage
import mx.yts.domain.model.MusicModel

class PlayerParams {
    companion object {
        const val paramKey = "music_id"
        const val route = "player/{$paramKey}"
        fun routeWithParam(id: String) = "player/$id"
    }
}


@Composable
fun Player(viewModel: PlayerViewModel, audioId: String?) {
    LaunchedEffect(key1 = "karim", block = {
        viewModel.getTrack(audioId!!)
    })
//    Log.d("MusicDetail","Player method")

    MusicDetailView(viewModel)
}

@Composable
fun MusicDetailView(audioInfoViewModel: PlayerViewModel) {

    val state = audioInfoViewModel.audioInfoUiState.collectAsState().value
    if (state is Result.Success) {
//        audioInfoViewModel.playMusic()
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            Pair(0.3f, Color(0xffD9D9D9)),
                            Pair(1f, Color(0xfff1f1f1)),
                            Pair(1f, Color(0xffffffff))
                        ),
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                artImage(state.data, audioInfoViewModel)
                seekBar(audioInfoViewModel)
                mediaActions(audioInfoViewModel)
            }
        }

    } else {

    }

}

@Composable
fun mediaActions(audioInfoViewModel: PlayerViewModel) {
    var isPlaying by remember { mutableStateOf(audioInfoViewModel.isPlaying()) }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(onClick = { }) {
            Icon(
                Icons.Rounded.SkipPrevious,
                contentDescription = null,// decorative element
                Modifier.size(48.dp, 48.dp),
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        IconButton(
            onClick = {
                if (isPlaying) {
                    audioInfoViewModel.pauseMusic()
                    isPlaying = false
                } else {
                    audioInfoViewModel.playMusic()
                    isPlaying = true
                }
            },
        ) {
            Icon(
                if (isPlaying) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                contentDescription = null,// decorative element
                Modifier.size(48.dp, 48.dp),
            )


        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        IconButton(onClick = { }) {
            Icon(
                Icons.Rounded.SkipNext,
                contentDescription = null,// decorative element
                Modifier.size(48.dp, 48.dp),
            )

        }
    }
}

@Composable
fun seekBar(audioInfoViewModel: PlayerViewModel) {
    var sliderValue by remember { mutableStateOf(audioInfoViewModel.getCurrentTime().toFloat()) }
    val duration = audioInfoViewModel.audioModel?.duration
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    var isSeeking by remember { mutableStateOf(false) }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            isSeeking = interaction is DragInteraction.Start
        }
    }

    audioInfoViewModel.setupTimer {
        if (!isSeeking) sliderValue = audioInfoViewModel.getCurrentTime().toFloat() / 1000
    }

    Column(Modifier.fillMaxWidth()) {
        val minutes = (sliderValue / 60).toInt()
        val seconds = (sliderValue % 60).toInt()
        Slider(value = sliderValue,
            valueRange = 0f..(duration?.inWholeSeconds ?: 0f).toFloat(),
            interactionSource = interactionSource,
            onValueChange = { value ->
                sliderValue = value
            },
            onValueChangeFinished = {
                audioInfoViewModel.seekTo(sliderValue * 1000)
            })
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "${if (minutes < 10) "0$minutes" else minutes}:${if (seconds < 10) "0$seconds" else seconds}")
            Text(text = "${if (duration?.minutes!! < 10) "0${duration.minutes}" else duration.minutes}:${if (duration.seconds!! < 10) "0${duration.seconds}" else duration.seconds}")
        }
    }
}

@Composable
fun artImage(data: MusicModel, audioInfoViewModel: PlayerViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val size = audioInfoViewModel.getScreenSize(LocalConfiguration.current)
        Spacer(modifier = Modifier.height((size.height * 0.2).dp))
        SharedElement(
            key = "picture - ${data.id}",
            screenKey = "picture2",
            transitionSpec = SharedElementsTransitionSpec(fadeMode = FadeMode.In),
        ) {
            GlideImage(
                imageModel = loadImage(item = data.artUrl, LocalContext.current),
                Modifier.size((size.width * 0.8).dp, (size.width * 0.8).dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = data.name)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = data.artist)
    }

}
