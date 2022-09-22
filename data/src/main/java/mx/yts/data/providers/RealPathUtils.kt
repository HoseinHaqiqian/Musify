package mx.yts.domain.providers

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.loader.content.CursorLoader

class RealPathUtil {

    fun getRealPathFromURI_API19(context: Context, uri: Uri): String? {
        var filePath = "";
        var wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        var id = wholeID.split(":")[1];

        var column = arrayOf(MediaStore.Images.Media.DATA);

        // where id is equal to
        var sel = MediaStore.Images.Media._ID + "=?";

        var cursor: Cursor = context.getContentResolver().query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            column, sel, arrayOf(id), null
        )!!;

        var columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }


    fun getRealPathFromURI_API11to18(context: Context, contentUri: Uri): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA);
        var result: String? = null;

        val cursorLoader = CursorLoader(
            context,
            contentUri, proj, null, null, null
        );
        val cursor = cursorLoader.loadInBackground();

        if (cursor != null) {
            val column_index =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
        }
        return result;
    }

    fun getRealPathFromURI_BelowAPI11(context: Context, contentUri: Uri): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA);
        val cursor = context.getContentResolver().query(contentUri, proj, null, null, null)!!;
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}