package com.adrian.droidcraft;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

public class MainActivity extends SherlockActivity {
	private AQuery aq;
	private String realm;
	private String guild;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		aq = new AQuery(this);
		aq.id(R.id.btn_display).clicked(this, "displayButtonClicked");
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

	public void displayButtonClicked(View button) {
		button.setEnabled(false);
		realm = aq.id(R.id.et_realm).getText().toString();
		guild = aq.id(R.id.et_guild).getText().toString();
		
		// TODO Verify that this text values are not null before sending.
		realm = realm.replace(" ", "%20");
		guild = guild.replace(" ", "%20");
		
		String url = "https://us.battle.net/api/wow/guild/" + realm + "/"+ guild;
		aq.ajax(url, JSONObject.class, this, "getRESTResponse");
	}
	
	public void getRESTResponse(String url, JSONObject json, AjaxStatus status) {
		aq.id(R.id.btn_display).getButton().setEnabled(true);
		
		if(json != null) {
			if(json.has("status")) {
				Toast.makeText(aq.getContext(), "Invalid Guild or Realm", Toast.LENGTH_LONG).show();
			} else {
				Intent guildLevelChart = new Intent(this, GuildLevelChartActivity.class);
				guildLevelChart.putExtra("realm", realm);
				guildLevelChart.putExtra("guild", guild);
				
				startActivity(guildLevelChart);
			}
		} else {
			Toast.makeText(aq.getContext(), "Invalid Guild or Realm", Toast.LENGTH_LONG).show();
		}
		
	}

}
