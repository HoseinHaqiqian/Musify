package mx.yts.domain.model

import android.net.Uri

class MusicModel(
    var id: Int,
    var name: String,
    var artist: String,
    var artUrl: Uri,
    var songUri: String,
    val sArtworkUri: Uri,
    val duration: DurationModel
)