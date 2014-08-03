package com.adrian.droidcraft;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.adrian.droidcraft.model.Guild;
import com.adrian.droidcraft.model.Member;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

/**
 * Activity created to learn how to implement visual charts using AChartEngine 1.1.0 and gson parsing from json
 * retrieved with async AQuery calls.
 * @author adrian
 *
 */
public class GuildLevelChartActivity extends Activity {

	private String guildName;
	private String realm;
	private Guild guild;

	private AQuery aq;
	private Gson gson;

	private GraphicalView mChart;
	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries mCurrentSeries;
    private XYSeriesRenderer mCurrentRenderer;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guild_level_chart);
		Bundle bundle = getIntent().getExtras();

		aq = new AQuery(this);
		realm = bundle.getString("realm");
		guildName = bundle.getString("guild");

		// TODO Escape this string values (realm and guild name)
		String url = "http://us.battle.net/api/wow/guild/" + realm + "/"
				+ guildName + "?fields=members";
		aq.ajax(url, JSONObject.class, this, "getRESTResponse");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guild_level_chart, menu);
		return true;
	}

	/**
	 * Callback for asynchronous call on OnCreate() for the WoW REST Guild service. Maps the response to a Guild 
	 * object and repaints chart.
	 * @param url
	 * @param json
	 * @param status
	 */
	public void getRESTResponse(String url, JSONObject json, AjaxStatus status) {
		if (json != null) {
			gson = new Gson();
			guild = gson.fromJson(json.toString(), Guild.class);
			addData();
			mChart.repaint();
		} else {
			Toast.makeText(aq.getContext(), "Error: " + status.getMessage(), Toast.LENGTH_LONG).show();
		}
		aq.id(R.id.loadingPanel).visibility(View.GONE);
	}
	
	/**
	 * Configures chart structure.
	 */
	private void initChart() {
		//initial configuration
        mCurrentSeries = new XYSeries("Loading serie...");
        mDataset.addSeries(mCurrentSeries);
        mCurrentRenderer = new XYSeriesRenderer();
        mRenderer.addSeriesRenderer(mCurrentRenderer);
        
        //visual customization
        mRenderer.setLabelsTextSize(18);
        mRenderer.setLegendTextSize(19);
        mRenderer.setAxisTitleTextSize(18);
        mRenderer.setBarSpacing(0.1);
        
        mRenderer.setXTitle("Level Range");
        mRenderer.setYTitle("Members");
        
        //item colors
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setAxesColor(Color.BLACK);
        mRenderer.setLabelsColor(Color.BLACK);
        mRenderer.setBackgroundColor(Color.WHITE);
        mRenderer.setMarginsColor(Color.WHITE);
        mRenderer.setXLabelsColor(Color.BLACK);
        mRenderer.setYLabelsColor(0, Color.BLACK);
        mRenderer.setXLabels(0);
        
        //additional features
        mRenderer.setDisplayValues(true);
        mRenderer.setZoomEnabled(true);
        mRenderer.setZoomButtonsVisible(true);
        
        //labels for the serie
        mRenderer.addXTextLabel(1, "1-9");
        mRenderer.addXTextLabel(2, "10-19");
        mRenderer.addXTextLabel(3, "20-29");
        mRenderer.addXTextLabel(4, "30-39");
        mRenderer.addXTextLabel(5, "40-49");
        mRenderer.addXTextLabel(6, "50-59");
        mRenderer.addXTextLabel(7, "60-69");
        mRenderer.addXTextLabel(8, "70-79");
        mRenderer.addXTextLabel(9, "80-89");
        mRenderer.addXTextLabel(10, "90");
    }
	
	/**
	 * If available, process the Guild object. In a real case scenario, the REST should have the required chart format, this process is
	 * expensive and should not be made like this.
	 */
	private void addData() {
        if(guild == null) {
        	return;
        }
        
        mCurrentSeries.setTitle(guild.getName() + " Guild Level Serie");
        mRenderer.setChartTitle(guild.getName());
        
        int[] ranges = new int[10];
        
        for(Member member : guild.getMembers()) {
        	int level = member.getCharacter().getLevel();
        	
        	if(level < 10) {
        		ranges[0] += 1;
        	} else if(level < 20) {
        		ranges[1] += 1;
        	} else if(level < 30) {
        		ranges[2] += 1;
        	} else if(level < 40) {
        		ranges[3] += 1;
        	} else if(level < 50) {
        		ranges[4] += 1;
        	} else if(level < 60) {
        		ranges[5] += 1;
        	} else if(level < 70) {
        		ranges[6] += 1;
        	} else if(level < 80) {
        		ranges[7] += 1;
        	} else if(level < 90) {
        		ranges[8] += 1;
        	} else {
        		ranges[9] += 1;
        	}
        }
        
        for(int i=0;i<10;i++) {
        	mCurrentSeries.add(i+1, ranges[i]);
        }
        
    }
	
	@Override
	protected void onResume() {
        super.onResume();
        LinearLayout layout = (LinearLayout) findViewById(R.id.ch_guildlevel);
        if (mChart == null) {
            initChart();
            addData();  
            mChart = ChartFactory.getBarChartView(this, mDataset, mRenderer, Type.DEFAULT);
            layout.addView(mChart);
        } else {
            mChart.repaint();
        }
    }

}
