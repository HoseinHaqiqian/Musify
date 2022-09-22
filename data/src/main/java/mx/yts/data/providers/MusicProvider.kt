package mx.yts.data.providers

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import dagger.hilt.android.qualifiers.ApplicationContext
import mx.yts.data.entities.MusicEntity
import java.io.InputStream
import javax.inject.Inject


class MusicProvider @Inject constructor(@ApplicationContext var applicationContext: Context) {
    private val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

    val sArtworkUri = Uri
        .parse("content://media/external/audio/albumart")

    private val projection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.DISPLAY_NAME,
        MediaStore.Audio.Media.DURATION
    )

    fun getAllMusics(): List<MusicEntity> {
        val songs: MutableList<MusicEntity> = ArrayList()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = applicationContext.contentResolver.query(
            uri,
            projection,
            selection,
            null,
            null
        )

        Log.d("Debug", "size ${cursor!!.count}")
        while (cursor!!.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
            val artist =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
            val title =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
            val songUri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                id
            )

//            val inputStream =
//                applicationContext.contentResolver.loadThumbnail(songUri, Size(640, 480), null)


            songs += MusicEntity(
                id = id.toInt(),
                name = title.toString(),
                artist = artist,
                songUri
            )
        }
        cursor.close()
        return songs
    }


}