package mx.yts.data.entities

import android.net.Uri

data class MusicEntity(
    var id: Int,
    var name: String,
    var artist: String,
    var artUrl: Uri,
    var songUri: String,
    val sArtworkUri: Uri,
    val duration: String
)