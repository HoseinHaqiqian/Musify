package mx.yts.data.providers

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import mx.yts.data.entities.MusicEntity
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
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.DATA
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

        Log.d("Debug", "xd ${cursor!!.count}")
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
            val artist =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
            val title =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
                    .replace(".mp3", "")
            val songUri =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
            val duration =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))

            val artUri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                id
            )
            val sArtworkUri = Uri
                .parse("content://media/external/audio/albumart")
            val uri =
                ContentUris.withAppendedId(sArtworkUri, id)
//            val inputStream =
//                applicationContext.contentResolver.loadThumbnail(songUri, Size(640, 480), null)


            songs += MusicEntity(
                id = id.toInt(),
                name = title.toString(),
                artist = artist,
                artUri,
                songUri,
                uri,
                duration,
            )
        }
        cursor.close()
        return songs
    }

    fun getMusic(id: String): MusicEntity {
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = applicationContext.contentResolver.query(
            uri,
            projection,
            selection,
            null,
            null
        )
        var musicEntity: MusicEntity? = null

        while (cursor!!.moveToNext()) {
            val localId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
            val artist =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
            val title =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
            val songUri =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
            val duration =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))

            val artUri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                localId
            )
            val sArtworkUri = Uri
                .parse("content://media/external/audio/albumart")
            val uri =
                ContentUris.withAppendedId(sArtworkUri, localId)
//            val inputStream =
//                applicationContext.contentResolver.loadThumbnail(songUri, Size(640, 480), null)

            Log.d("Comparison", "local ${localId} == $id")
            if (localId.toString() == id) {
                musicEntity = MusicEntity(
                    id = id.toInt(),
                    name = title.toString(),
                    artist = artist,
                    artUri,
                    songUri,
                    uri,
                    duration,
                )
                cursor.close()
                return musicEntity
            }
        }
        throw java.lang.Exception("No such audio")
    }


}