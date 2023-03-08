package mx.yts.musiclist.ui

import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import mx.yts.musiclist.R

@Composable
fun NoMusicResult(){
    Image(
        painter = painterResource(id = R.mipmap.music_empty),
        contentDescription = null
    )
}