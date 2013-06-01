package pl.michalu.trr.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class RadioWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		ComponentName thisWidget = new ComponentName(context, RadioWidgetProvider.class);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		Intent intent = new Intent(context, RadioService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
		remoteViews.setOnClickPendingIntent(R.id.layout, pendingIntent);
		appWidgetManager.updateAppWidget(allWidgetIds, remoteViews);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		if (RadioService.mp.isPlaying()) {
			RadioService.mp.stop();
		}
		Intent intent = new Intent(context, RadioService.class);
		context.stopService(intent);
		super.onDisabled(context);
	}
}
