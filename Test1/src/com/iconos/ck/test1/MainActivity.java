package com.iconos.ck.test1;

import com.androidquery.AQuery;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
/*		Button btn = (Button)findViewById(R.id.b_Saluda);
		
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText et = (EditText)findViewById(R.id.et_Nombre);
				Toast.makeText(MainActivity.this, "Hola "+et.getText(), Toast.LENGTH_LONG).show();
			}
		});*/
		
		AQuery aq = new AQuery(this);
		aq.id(R.id.b_Saluda).clicked(this, "enviarTostado");
		
	}
	
	public void enviarTostado(){
		AQuery aq = new AQuery(this);
		Toast.makeText(this, "Hola "+aq.id(R.id.et_Nombre).getText(), Toast.LENGTH_LONG).show();
	}
	
	

	

}
