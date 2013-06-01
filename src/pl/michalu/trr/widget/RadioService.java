package pl.michalu.trr.widget;

import java.io.IOException;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.widget.RemoteViews;

public class RadioService extends Service {

	public static boolean isRadioOn = false;
	public static MediaPlayer mp = new MediaPlayer();
	public RemoteViews remoteViews;

	@Override
	public void onStart(Intent intent, int startId) {
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
		ComponentName thisWidget = new ComponentName(getApplicationContext(), RadioWidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		for (int widgetId : allWidgetIds) {
			remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.widget_layout);
			if (!isRadioOn) {
				remoteViews.setTextViewText(R.id.update, getString(R.string.dots));
				appWidgetManager.updateAppWidget(widgetId, remoteViews);
				try {
					mp.reset();
					mp.setDataSource(getString(R.string.stream));
					mp.prepareAsync();
					mp.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							// TODO Auto-generated method stub
							mp.start();
							isRadioOn = true;
							remoteViews.setTextViewText(R.id.update, getString(R.string.off));
							ComponentName thisWidget = new ComponentName(getApplicationContext(), RadioWidgetProvider.class);
							AppWidgetManager.getInstance(getApplicationContext()).updateAppWidget(thisWidget, remoteViews);

						}
					});

					// pb.setVisibility(View.GONE);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				mp.stop();
				isRadioOn = false;
				remoteViews.setTextViewText(R.id.update, getString(R.string.on));
				appWidgetManager.updateAppWidget(widgetId, remoteViews);
				stopSelf();
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}