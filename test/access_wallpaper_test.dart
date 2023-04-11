import 'dart:typed_data';

import 'package:flutter_test/flutter_test.dart';
import 'package:access_wallpaper/access_wallpaper.dart';
import 'package:access_wallpaper/access_wallpaper_platform_interface.dart';
import 'package:access_wallpaper/access_wallpaper_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAccessWallpaperPlatform
    with MockPlatformInterfaceMixin
    implements AccessWallpaperPlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<Uint8List?> getWallpaper(int wallPaperFlag) {
    throw UnimplementedError();
  }
}

void main() {
  final AccessWallpaperPlatform initialPlatform =
      AccessWallpaperPlatform.instance;

  test('$MethodChannelAccessWallpaper is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelAccessWallpaper>());
  });
}
