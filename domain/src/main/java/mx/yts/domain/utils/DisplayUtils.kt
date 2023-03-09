package mx.yts.domain.utils

import android.content.res.Configuration
import android.util.DisplayMetrics
import android.util.Size
import mx.yts.domain.model.DurationModel
import mx.yts.domain.model.MusicModel

class DisplayUtils {

    companion object {
        fun getScreenSize(configuration: Configuration): Size {
            return Size(configuration.screenWidthDp, configuration.screenHeightDp)
        }

        fun twoDigitFormat(value: Int): String {
            return if (value < 10) "0${value}" else value.toString()
        }

        fun formattedMusicTime(duration: DurationModel):String{
            return "${twoDigitFormat(duration.minutes)}:${twoDigitFormat(duration.seconds)}"
        }
    }

}