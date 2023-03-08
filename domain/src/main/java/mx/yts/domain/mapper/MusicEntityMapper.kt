package mx.yts.domain.mapper

import mx.yts.data.entities.MusicEntity
import mx.yts.domain.model.DurationModel
import mx.yts.domain.model.MusicModel
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

class MusicEntityMapper @Inject constructor() {
    fun mapTo(from: MusicEntity): MusicModel {
        val milliseconds = from.duration.toLong()
        val minutes = (milliseconds / 1000 / 60).toInt()
        val seconds = (milliseconds / 1000 % 60).toInt()
        val inSeconds = milliseconds.milliseconds.inWholeSeconds
        return MusicModel(
            from.id,
            from.name,
            artist = from.artist,
            songUri = from.songUri,
            artUrl = from.artUrl,
            duration = DurationModel(minutes,seconds,inSeconds),
            sArtworkUri = from.sArtworkUri
        )
    }

}