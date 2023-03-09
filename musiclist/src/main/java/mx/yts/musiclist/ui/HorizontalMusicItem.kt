package mx.yts.musiclist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import mx.yts.common.utils.loadImage
import mx.yts.domain.model.MusicModel
import mx.yts.domain.utils.DisplayUtils
import mx.yts.domain.utils.DisplayUtils.Companion.getScreenSize


@Composable
fun HorizontalMusicItem(musicModel: MusicModel, openPlayer: (id: String) -> Unit) {
    val imageSize = (getScreenSize(LocalConfiguration.current).width * 0.2).dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(imageSize + 10.dp)
            .padding(5.dp),
    ) {
        GlideImage(
            imageModel = loadImage(musicModel.artUrl, LocalContext.current),
            Modifier
                .size(imageSize, imageSize)
                .clip(RoundedCornerShape(5.dp))
                .shadow(4.dp, RectangleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Text(musicModel.name)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(musicModel.artist)
                Text(DisplayUtils.formattedMusicTime(musicModel.duration))
            }
        }
    }

}