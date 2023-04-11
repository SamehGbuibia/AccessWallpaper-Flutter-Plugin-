import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:access_wallpaper/access_wallpaper_method_channel.dart';

void main() {
  MethodChannelAccessWallpaper platform = MethodChannelAccessWallpaper();
  const MethodChannel channel = MethodChannel('access_wallpaper');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });
}
