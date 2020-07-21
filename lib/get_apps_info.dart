import 'dart:async';

import 'package:flutter/services.dart';

class GetAppsInfo {
  static const MethodChannel _channel =
      const MethodChannel('get_apps_info');


  static Future<List> get getApplicationInfo async {
    var result = await _channel.invokeMethod('getApplicationInfo');
    return result;
  }

}
