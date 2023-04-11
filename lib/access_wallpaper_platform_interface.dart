import 'dart:typed_data';

import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'access_wallpaper_method_channel.dart';

abstract class AccessWallpaperPlatform extends PlatformInterface {
  /// Constructs a AccessWallpaperPlatform.
  AccessWallpaperPlatform() : super(token: _token);

  static final Object _token = Object();

  static AccessWallpaperPlatform _instance = MethodChannelAccessWallpaper();

  /// The default instance of [AccessWallpaperPlatform] to use.
  ///
  /// Defaults to [MethodChannelAccessWallpaper].
  static AccessWallpaperPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [AccessWallpaperPlatform] when
  /// they register themselves.
  static set instance(AccessWallpaperPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<Uint8List?> getWallpaper(int wallPaperFlag) {
    throw UnimplementedError('getWallpaper() has not been implemented.');
  }
}
