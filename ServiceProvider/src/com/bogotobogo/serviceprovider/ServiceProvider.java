package com.bogotobogo.serviceprovider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceProvider extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	        
		Button start = (Button)findViewById(R.id.startButton);
		Button stop = (Button)findViewById(R.id.stopButton);
	        
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
	}
	
	public void onClick(View src) {    
		switch (src.getId()) {    
			case R.id.startButton:        
				startService(new Intent(this, SimpleService.class));      
				break;    
			case R.id.stopButton:          
				stopService(new Intent(this, SimpleService.class));      
				break;    
		}  
	}
}