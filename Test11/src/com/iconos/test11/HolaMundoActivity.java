package com.iconos.test11;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.androidquery.AQuery;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class HolaMundoActivity extends SherlockActivity {

	AQuery aq;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hola_mundo);
		
		aq = new AQuery(this);
		aq.id(R.id.holaMundo_b_saluda).clicked(this, "saluda");
		 
		//aq.id(R.id.sp_ideas).
		ArrayList<String> items = new ArrayList<String>();
		items.add("Uno");
		items.add("Dos");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);
		aq.id(R.id.sp_bonito).adapter(adapter);
		
	}

	public void saluda(){
		Toast.makeText(this, aq.id(R.id.holaMundo_ed_Numero).getText(), Toast.LENGTH_LONG).show();
	}


}
