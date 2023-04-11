import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:access_wallpaper/access_wallpaper.dart';
import 'package:permission_handler/permission_handler.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  Uint8List? wallpaper;

  @override
  void initState() {
    super.initState();
    initWallpaper();
  }

  Future<void> initWallpaper() async {
    //request manage storage permission
    await Permission.manageExternalStorage.request();

    //check if the permission is granted
    if (await Permission.manageExternalStorage.isGranted) {
      //use the plugin to get the home screen wallpaper by the homeScreenFlag
      //(you can get the lock screen wallpaper by the lockScreenFlag)
      // /!\ if you put different values the plugin will always return null
      wallpaper =
          await AccessWallpaper().getWallpaper(AccessWallpaper.homeScreenFlag);

      //update the ui if possible
      if (!mounted) return;
      setState(() {});
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: wallpaper == null
              ? const CircularProgressIndicator()
              : Image.memory(wallpaper!),
        ),
      ),
    );
  }
}
