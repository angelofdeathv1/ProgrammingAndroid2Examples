package com.angeldev;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.util.Log;

public class Helper {

	private StringBuilder stringBuilder = null;
	private JSONArray jArray = null;
	private String userID = null;

	public InputStream getEntityContent(String scriptName,
			ArrayList<NameValuePair> nameValuePairs) {

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(scriptName);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			return entity.getContent();
		} catch (Exception e) {
			Log.e("HTTP Connection", "Error in http connection" + e.toString());
			return null;
		}

	}

	public String getEntityContentToString(InputStream entityContent) {

		if (entityContent == null) {
			return null;
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entityContent, "iso-8859-1"), 8);
			stringBuilder = new StringBuilder();
			stringBuilder.append(reader.readLine() + "\n");
			String line = "0";
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			entityContent.close();
			Log.e("Contenido", stringBuilder.toString());
			return stringBuilder.toString();
		} catch (Exception e) {
			Log.e("Stream conversion",
					"Error converting result " + e.toString());
			return null;
		}

	}

	public String getResult(String entityStringContent) {

		if (entityStringContent == null) {
			return "-1";
		}

		try {
			userID = null;
			jArray = new JSONArray(entityStringContent);
			JSONObject json_data = null;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				userID = json_data.getInt("id") + "";
				System.out
						.println("TODOS LOS RECORDS" + json_data.getInt("id"));
			}
		} catch (JSONException e1) {
			return "-1";
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return userID;
	}

}