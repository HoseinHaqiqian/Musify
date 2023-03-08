package mx.yts.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri

fun loadImage(item: Uri, context: Context): Bitmap {
    var imgbyte: ByteArray?
    val mmr = MediaMetadataRetriever()

    mmr.setDataSource(context.applicationContext, item)

    imgbyte = mmr.embeddedPicture

    if (imgbyte != null) {
        return BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.size, BitmapFactory.Options())
    }
    throw Exception("No bitmap")
}
