package mx.yts.data.repositories.music

import mx.yts.data.entities.MusicEntity

interface  MusicRepository  {
    fun getAllMusics() : List<MusicEntity>
}