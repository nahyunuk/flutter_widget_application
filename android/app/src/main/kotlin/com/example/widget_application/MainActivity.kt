package com.example.widget_application

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "FLUTTER_METHOD_CHANNEL")
            .setMethodCallHandler { call, _ ->
                if (call.method == "updateCarImage") {

                    val carWidgetsIds = AppWidgetManager.getInstance(application).getAppWidgetIds(
                        ComponentName(application, NewAppWidget::class.java)
                    )

                    val intent = Intent(this, NewAppWidget::class.java)
                    intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE)

                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, carWidgetsIds)
                    sendBroadcast(intent)
                } else {

                }
            }
    }
}
