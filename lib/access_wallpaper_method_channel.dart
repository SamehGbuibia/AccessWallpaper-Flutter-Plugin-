import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'access_wallpaper_platform_interface.dart';

/// An implementation of [AccessWallpaperPlatform] that uses method channels.
class MethodChannelAccessWallpaper extends AccessWallpaperPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('access_wallpaper');

  @override
  Future<Uint8List?> getWallpaper(int wallPaperFlag) async {
    return await methodChannel.invokeMethod('getWallpaper', wallPaperFlag);
  }
}
