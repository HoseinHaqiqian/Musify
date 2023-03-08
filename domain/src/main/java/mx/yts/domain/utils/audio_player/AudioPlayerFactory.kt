package mx.yts.domain.utils.audio_player

import dagger.assisted.AssistedFactory

@AssistedFactory
interface AudioPlayerFactory {
    fun create(audioPath : String) : AudioPlayer
}