package com.javacodegeeks.android.androidasynctasktutorial;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends Activity {
	final Context context = this;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	private class LoadWebPageASYNC extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {

			WebView webView = (WebView) findViewById(R.id.webView);
			webView.getSettings().setJavaScriptEnabled(true);
			webView.loadUrl(urls[0]);

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			System.out.println("TERMINE!!!!!!");
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
	}

	public void dummyFunc(View view) {
		Toast.makeText(getApplicationContext(), "Button Clicked",
				Toast.LENGTH_SHORT).show();
	}

	public void readWebpage(View view) {
		LoadWebPageASYNC task = new LoadWebPageASYNC();
		task.execute(new String[] { "http://www.javacodegeeks.com" });

	}
}