package com.techblogon.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	private static final String TAG = "MyService";
	Time now = new Time();

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "Congrats! MyService Created", Toast.LENGTH_LONG)
				.show();
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onStart(Intent intent, int startId) {

		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
		now.setToNow();
		System.out.println(now.toString());
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "MyService Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
		now.setToNow();
		System.out.println(now.toString());
	}
}
