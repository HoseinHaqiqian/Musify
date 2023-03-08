package mx.yts.domain.utils.audio_player

import android.media.MediaPlayer
import android.os.Build
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class AudioPlayer @AssistedInject constructor(@Assisted audioPath: String) {
    var mediaPlayer: MediaPlayer = MediaPlayer()

    init {
        try {
            mediaPlayer.setDataSource(audioPath)
            mediaPlayer.prepare()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    fun play() {
        try {
            mediaPlayer.start()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun jumpTo(milliSecond: Int) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) mediaPlayer.seekTo(
                milliSecond.toLong(),
                MediaPlayer.SEEK_CLOSEST
            ) else mediaPlayer.seekTo(milliSecond)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun pause() {
        try {
            mediaPlayer.pause()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun getCurrentTime(): Int {
        return mediaPlayer.currentPosition
    }

    fun onDestroy(){
        mediaPlayer.release()
    }
}

