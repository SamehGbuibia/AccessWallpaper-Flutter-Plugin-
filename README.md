# AccessWallpaper Flutter Plugin - README

The AccessWallpaper Flutter plugin is a plugin that provides functionality to get device wallpaper. This plugin currently only supports Android devices.

## Installation

To use this plugin, add `access_wallpaper` as a [dependency in your pubspec.yaml file](https://flutter.dev/docs/development/packages-and-plugins/using-packages).

### Permissions

To use this plugin, you need to add the following permission to your app's AndroidManifest.xml file:

```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
```

You also need to request the permission at runtime before using the plugin. You can use a package like [permission_handler](https://pub.dev/packages/permission_handler) to request the permission.

### Usage

To use this plugin, you need to create an instance of the `AccessWallpaper` class, and call the `getWallpaper` method with lock or home flag. This method returns a `Uint8List` containing the desired phone wallpaper. 

```dart
import 'package:access_wallpaper/access_wallpaper.dart';

final AccessWallpaper accessWallpaper = AccessWallpaper();

Uint8List? wallpaperBytes = await accessWallpaper.getWallpaper(AccessWallpaper.homeScreenFlag);
```

You can then use the `wallpaperBytes` in your app 

## Issues

Please report any issues or bugs you encounter using the [issue tracker](https://github.com/your_username/access_wallpaper/issues) on Github.

## Contributing

Contributions are welcome! Please feel free to fork the repository and submit a pull request with your changes.

## Author

accessWallpaper plugin is developed by Sameh Gbibia. You can contact me at gbuibiasameh@gmail.com.

## License

accessWallpaper plugin is released under the Apache License 2.0. See the LICENSE file for details.
```