package hosein.haqiqian.player.utils

import android.util.Log
import java.io.*
import javax.inject.Inject

class FileProcessor @Inject constructor() {

    fun fileToBytes(file: File): ByteArray {
        val size = file.length()
        Log.d("BytesInfo", "Size ${size}")
        val bytes = ByteArray(size.toInt())
        Log.d("BytesInfo", "Bytes ${bytes}")
        try {
            val buf = BufferedInputStream(FileInputStream(file))
            buf.read(bytes, 0, bytes.size)
            buf.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Log.d("BytesInfo", "Length ${bytes.size}")
        return bytes
    }

}