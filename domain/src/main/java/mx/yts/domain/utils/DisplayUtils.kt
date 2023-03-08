package mx.yts.domain.utils

import android.content.res.Configuration
import android.util.DisplayMetrics
import android.util.Size

class DisplayUtils {

    companion object {
        fun getScreenSize(configuration: Configuration): Size {
            return Size(configuration.screenWidthDp, configuration.screenHeightDp)
        }
    }

}