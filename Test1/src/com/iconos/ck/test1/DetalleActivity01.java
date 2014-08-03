package com.iconos.ck.test1;

import java.util.HashMap;

import com.androidquery.AQuery;
import com.iconos.ck.test1.modelo.Global;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DetalleActivity01 extends Activity {

	AQuery aq;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_activity01);
		
		aq = new AQuery(this);
		
		aq.id(R.id.tv_Marca).text(Global.auto.marca);
		aq.id(R.id.tv_Modelo).text(Global.auto.modelo);
		aq.id(R.id.tv_Anio).text(Global.auto.anio+"");
		
		aq.id(R.id.b_Enviar).clicked(this,"enviar");
		
	}
	
	public void enviar(){
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		
//		   HashMap<String, Object> params = new HashMap<String, Object>();
//
//           params.put("method", "u");
//           params.put("p", p.propuestaId);
//           params.put("A1", mCurrent);
//
//           mAq.progress(mProgress).ajax(getUrlBase(), params, JSONObject.class, GalleryActivity.this, "onFotoUploaded");
//		
		
	}



}
