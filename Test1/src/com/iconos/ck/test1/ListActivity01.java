package com.iconos.ck.test1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.iconos.ck.test1.modelo.Auto;
import com.iconos.ck.test1.modelo.AutosAdapter;
import com.iconos.ck.test1.modelo.Global;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ListActivity01 extends Activity {

	AQuery aq;
	ProgressDialog pd;
	AutosAdapter listado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_list_activity01);
		
		listado = new AutosAdapter(this);
		
		//el boton al momendo de dar clik manda llamar listar
		aq = new AQuery(this);
//		aq.id(R.id.b_Test).clicked(this, "listar");
		
		//cargando valores del dialogo
		pd = new ProgressDialog(this);
		pd.setIndeterminate(true);
		pd.setCancelable(false);
		pd.setInverseBackgroundForced(false);
		pd.setCanceledOnTouchOutside(false);
		pd.setTitle("Descargando...");
		
		//asignar el adapter
		//aq.id(R.id.lv_Listado).adapter(listado);
		
		//transicion
		//aq.id(R.id.lv_Listado).itemClicked(this,"verDetalle");
				
		//asiganando adapetr y transicion
		aq.id(R.id.lv_Listado).adapter(listado).itemClicked(this,"verDetalle");
		
		listar();
	}
	
	public void listar(){
		//aq.ajax(getString(R.string.API_Listar),JSONObject.class, this, "recibeListado");
		aq.progress(pd).ajax(getString(R.string.API_Listar),JSONObject.class, this, "recibeListado");	
	}
	
	public void verDetalle(AdapterView<?> parent, View v, int pos, long id){
		Log.d("ListActivity","dentro "+pos);
		Global.auto = listado.getItem(pos);
		Intent intent = new Intent(this, DetalleActivity01.class);
		startActivity(intent);
	}
	
	public void recibeListado(String url, JSONObject obj, AjaxStatus status){
		if (obj==null){
			Toast.makeText(this, "Status " +status.getError(), Toast.LENGTH_LONG).show();
			return;
		}
		
		
		
		try {
			
			//Log.d("ListActivity01",obj.toString(2));
			
			listado.clear();
			
			if (obj.has("data")) {
				JSONArray arreglo = obj.getJSONArray("data");
				for(int i=0; i<arreglo.length(); i++){
					JSONObject objeto = arreglo.getJSONObject(i);
					listado.add(Auto.crearAuto(objeto));
				}
			}
			
			listado.notifyDataSetChanged();
			
			
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


}
