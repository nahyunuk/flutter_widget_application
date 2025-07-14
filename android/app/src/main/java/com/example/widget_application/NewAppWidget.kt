package com.example.widget_application

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.widget.RemoteViews

class NewAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {

    }

    override fun onDisabled(context: Context) {

    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)

    val carImagePath =
        context.getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE)
            .getString("flutter.car_image", null) ?: return

    views.setImageViewBitmap(R.id.car_image, BitmapFactory.decodeFile(carImagePath))

    appWidgetManager.updateAppWidget(appWidgetId, views)
}