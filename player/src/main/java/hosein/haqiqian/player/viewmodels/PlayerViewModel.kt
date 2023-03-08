package hosein.haqiqian.player.viewmodels

import android.content.res.Configuration
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hosein.haqiqian.player.utils.FileProcessor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.yts.common.utils.Result
import mx.yts.domain.model.MusicModel
import mx.yts.domain.usecases.audioinfo.GetAudioInfoUseCaseFactory
import mx.yts.domain.utils.DisplayUtils
import mx.yts.domain.utils.audio_player.AudioPlayer
import mx.yts.domain.utils.audio_player.AudioPlayerFactory
import java.io.File
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@HiltViewModel
class PlayerViewModel @Inject constructor(

) : ViewModel() {


    @Inject
    lateinit var audioPlayerFactory: AudioPlayerFactory

    @Inject
    lateinit var getAudioInfoUseCaseFactory: GetAudioInfoUseCaseFactory

    @Inject
    lateinit var fileProcessor: FileProcessor

    private var timer: Timer? = null
    private var seekTimer: Timer? = null

    private val audioInfoState =
        MutableStateFlow<Result<MusicModel>>(Result.Loading)

    val audioInfoUiState: StateFlow<Result<MusicModel>> = audioInfoState

    val audioModel: MusicModel?
        get() {
            if (audioInfoState.value is Result.Success) {
                return (audioInfoState.value as Result.Success).data
            }
            return null
        }

    private var audioPlayer: AudioPlayer? = null
    private var trackRequested = false
    fun getTrack(audioId: String) {
        Log.d("RepetitionCheck", "Calling GetTrack")
        viewModelScope.launch {
            audioInfoState.value = Result.Loading
            getAudioInfoUseCaseFactory.create(audioId).invoke().let {
                if (audioInfoState.value !is Result.Success) {
                    audioInfoState.value = Result.Success(it)
                }
                if (audioPlayer == null) {
                    audioPlayer = audioPlayerFactory.create(it.songUri)
                }
            }
        }
    }

    fun getScreenSize(current: Configuration) = DisplayUtils.getScreenSize(current)


    fun getAudioBytes(): ByteArray? {
        if (audioModel != null)
            return fileProcessor.fileToBytes(File(audioModel!!.songUri))
        return ByteArray(0)
    }

    fun seekTo(value: Float) {
        audioPlayer!!.jumpTo(value.toInt())
    }

    fun playMusic() {
        audioPlayer!!.play()
    }

    fun isPlaying(): Boolean = audioPlayer?.mediaPlayer?.isPlaying == true

    fun getCurrentTime(): Int {
        return audioPlayer?.getCurrentTime() ?: 0
    }

    fun pauseMusic() {
        audioPlayer?.pause()
    }


    fun setupTimer(unit: () -> Unit) {
        if (timer == null) {
            timer = Timer()
            timer!!.schedule(object : TimerTask() {
                override fun run() {
                    unit.invoke()
                }
            }, 1000, 1000L)
        }

    }

    fun safeSeek(value: Float) {
        if (seekTimer == null) seekTimer = Timer()
        seekTimer!!.cancel()
        seekTimer!!.schedule(object : TimerTask() {
            override fun run() {
                seekTimer!!.cancel()
                seekTo(value)
            }
        }, 0, 300L)
    }

    override fun onCleared() {
        audioPlayer?.onDestroy()
        timer?.cancel()
        seekTimer?.cancel()
        super.onCleared()
    }
}