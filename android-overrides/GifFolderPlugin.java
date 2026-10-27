package APP_PACKAGE;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import androidx.activity.result.ActivityResult;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.Locale;

@CapacitorPlugin(name = "GifFolder")
public class GifFolderPlugin extends Plugin {
  private static final String PREFS = "workout_media_access";
  private static final String TREE_URI = "workout_gif_tree_uri";

  private boolean isMedia(String name) {
    String n = name.toLowerCase(Locale.ROOT);
    return n.endsWith(".gif") || n.endsWith(".webp") || n.endsWith(".png")
      || n.endsWith(".jpg") || n.endsWith(".jpeg") || n.endsWith(".mp4")
      || n.endsWith(".webm") || n.endsWith(".mkv") || n.endsWith(".3gp")
      || n.endsWith(".m4v") || n.endsWith(".mov");
  }

  @PluginMethod
  public void requestAccess(PluginCall call) {
    try {
      Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
      intent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
      startActivityForResult(call, intent, "folderPickerResult");
    } catch (Exception e) {
      call.reject("Could not open Android folder picker: " + e.getMessage());
    }
  }

  @ActivityCallback
  private void folderPickerResult(PluginCall call, ActivityResult result) {
    if (call == null) return;
    if (result.getResultCode() != Activity.RESULT_OK || result.getData() == null) {
      call.resolve(new JSObject().put("granted", false).put("cancelled", true));
      return;
    }
    Uri treeUri = result.getData().getData();
    if (treeUri == null) {
      call.reject("No folder was selected.");
      return;
    }
    try {
      int flags = result.getData().getFlags()
        & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
      getContext().getContentResolver().takePersistableUriPermission(treeUri, flags);
      getContext().getSharedPreferences(PREFS, 0).edit().putString(TREE_URI, treeUri.toString()).apply();
      JSObject response = new JSObject();
      response.put("granted", true);
      response.put("uri", treeUri.toString());
      response.put("message", "Folder access saved.");
      call.resolve(response);
    } catch (Exception e) {
      call.reject("Could not retain folder permission: " + e.getMessage());
    }
  }

  @PluginMethod
  public void listGifs(PluginCall call) {
    SharedPreferences prefs = getContext().getSharedPreferences(PREFS, 0);
    String saved = prefs.getString(TREE_URI, null);
    JSArray files = new JSArray();
    JSObject response = new JSObject();
    if (saved == null) {
      response.put("files", files);
      response.put("exists", false);
      response.put("granted", false);
      response.put("message", "Select the Workout_gif folder first.");
      call.resolve(response);
      return;
    }
    Uri treeUri = Uri.parse(saved);
    ContentResolver resolver = getContext().getContentResolver();
    Cursor cursor = null;
    try {
      String treeDocumentId = DocumentsContract.getTreeDocumentId(treeUri);
      Uri childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(treeUri, treeDocumentId);
      String[] projection = new String[] {
        DocumentsContract.Document.COLUMN_DOCUMENT_ID,
        DocumentsContract.Document.COLUMN_DISPLAY_NAME,
        DocumentsContract.Document.COLUMN_MIME_TYPE
      };
      cursor = resolver.query(childrenUri, projection, null, null, null);
      if (cursor != null) {
        int idCol = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DOCUMENT_ID);
        int nameCol = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME);
        while (cursor.moveToNext()) {
          String name = nameCol >= 0 ? cursor.getString(nameCol) : "";
          String docId = idCol >= 0 ? cursor.getString(idCol) : null;
          if (docId == null || name == null || !isMedia(name)) continue;
          Uri fileUri = DocumentsContract.buildDocumentUriUsingTree(treeUri, docId);
          JSObject file = new JSObject();
          file.put("name", name);
          file.put("path", fileUri.toString());
          file.put("webPath", fileUri.toString());
          files.put(file);
        }
      }
      response.put("files", files);
      response.put("exists", true);
      response.put("granted", true);
      response.put("folderUri", saved);
      call.resolve(response);
    } catch (SecurityException e) {
      prefs.edit().remove(TREE_URI).apply();
      response.put("files", files);
      response.put("exists", false);
      response.put("granted", false);
      response.put("message", "Folder permission was revoked. Select the folder again.");
      call.resolve(response);
    } catch (Exception e) {
      call.reject("Could not list the selected media folder: " + e.getMessage());
    } finally {
      if (cursor != null) cursor.close();
    }
  }
}
