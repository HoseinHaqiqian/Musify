package mx.yts.domain.mapper

import mx.yts.data.entities.MusicEntity
import mx.yts.domain.model.MusicModel
import javax.inject.Inject

class MusicEntityMapper @Inject constructor() {
    fun mapTo(from: MusicEntity): MusicModel {
        return MusicModel(from.id, from.name, artist = from.artist, artPat = from.artUri)
    }

}