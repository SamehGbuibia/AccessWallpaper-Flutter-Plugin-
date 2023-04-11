import 'dart:typed_data';

import 'access_wallpaper_platform_interface.dart';

class AccessWallpaper {
  static const int homeScreenFlag = 1;
  static const int lockScreenFlag = 2;

  Future<Uint8List?> getWallpaper(int wallpaperFlag) {
    if (wallpaperFlag < homeScreenFlag || wallpaperFlag > lockScreenFlag) {
      return Future.value(null);
    }
    return AccessWallpaperPlatform.instance.getWallpaper(wallpaperFlag);
  }
}
