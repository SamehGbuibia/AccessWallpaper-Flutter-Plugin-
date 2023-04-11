package sameh.gbibia.access_wallpaper_example

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.ParcelFileDescriptor
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.io.ByteArrayOutputStream


class MainActivity: FlutterActivity() {
    private val WALLPAPERCHANNEL = "access_wallpaper"

    @RequiresApi(VERSION_CODES.N)
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, WALLPAPERCHANNEL).setMethodCallHandler {
            // This method is invoked on the main thread.
                call, result ->
            if (call.method == "getWallpaper") {
                val wallpaper = getWallpaper(call.arguments as Int)
                if (wallpaper != null) {
                    result.success(wallpaper)
                } else {
                    result.error("UNAVAILABLE", "Wallpaper not available.", null)
                }
            } else {
                result.notImplemented()
            }
        }
    }
    @SuppressLint("MissingPermission")
    private fun getWallpaper (wallpaperFlag:Int): ByteArray? {
        val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(applicationContext)
        if (VERSION.SDK_INT >= VERSION_CODES.N) {
            val wallpaper = wallpaperManager.getWallpaperFile(wallpaperFlag)
            if(wallpaper!=null){
                return ParcelFileDescriptor.AutoCloseInputStream(wallpaper).readBytes()
            }
        }
        val d = wallpaperManager.drawable
        val bitmap = (d as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val bitmapdata: ByteArray = stream.toByteArray()
        return bitmapdata
    }
}
