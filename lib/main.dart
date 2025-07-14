import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart';
import 'package:path_provider/path_provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
  runApp(MaterialApp(home: WidgetTestApp()));
}

class WidgetTestApp extends StatelessWidget {
  const WidgetTestApp({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          buildImage(
            'https://pngimg.com/uploads/car_crash/car_crash_PNG39.png',
          ),
          buildImage(
            'https://pngimg.com/uploads/car_crash/car_crash_PNG38.png',
          ),
        ],
      ),
    );
  }

  Widget buildImage(String imageUrl) => GestureDetector(
    onTap: () async {
      final result = await get(Uri.parse(imageUrl));
      final cacheDirectory = await getApplicationCacheDirectory();

      debugPrint('${result.headers}');

      final suffix = result.headers['content-type']?.split('/').last ?? '';
      final filePath = '${cacheDirectory.path}/carImage.$suffix';
      final carImageFile = File(filePath);
      await carImageFile.writeAsBytes(result.bodyBytes);

      (await SharedPreferences.getInstance()).setString("car_image", filePath);
      MethodChannel(
        "FLUTTER_METHOD_CHANNEL",
      ).invokeMapMethod<String, dynamic>('updateCarImage');
    },
    child: Image.network(imageUrl),
  );
}
