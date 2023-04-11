package sameh.gbibia.access_wallpaper

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
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
import android.content.Context

/** AccessWallpaperPlugin */
class AccessWallpaperPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var context : Context
  
  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "access_wallpaper")
    channel.setMethodCallHandler(this)
    context = flutterPluginBinding.getApplicationContext();
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
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

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
  
  @SuppressLint("MissingPermission")
  private fun getWallpaper (wallpaperFlag:Int): ByteArray? {
      val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(context)
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