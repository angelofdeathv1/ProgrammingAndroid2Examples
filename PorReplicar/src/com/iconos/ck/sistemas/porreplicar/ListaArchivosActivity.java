package com.iconos.ck.sistemas.porreplicar;

import java.util.List;

import com.androidquery.AQuery;
import com.iconos.ck.sistemas.porreplicar.model.Registro;
import com.iconos.ck.sistemas.porreplicar.model.RenglonAdapter;
import com.iconos.ck.sistemas.porreplicar.model.dao.RegistroDao;

import com.iconos.ck.sistemas.porreplicar.R;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;

public class ListaArchivosActivity extends Activity {

	AQuery aq;
	RenglonAdapter lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_archivos);
		
		aq = new AQuery(this);
		
		lista = new RenglonAdapter(this);
		
		 
//		aq.id(R.id.lv_Listado).adapter(listado);
		aq.id(R.id.listaArchivo_lv_lista).adapter(lista);
		
		
		
		lista.clear();
		
		lista.add(Registro.crearRegistro("Sonoyta", 300, "","CKMX210000"));
		lista.add(Registro.crearRegistro("Tlaxcala y 12", 356, "", "CKSL130000"));
		
		lista.notifyDataSetChanged();
		
//		RegistroDao dao = new RegistroDao(getApplicationContext());
//		dao.insert(Registro.crearRegistro("Test", 400, ""));
//	
//		List<Registro> registros = dao.listAll();
//		Log.v("crash", registros.size()+"");
//		Log.v("crash", registros.get(0).getNombreTienda()); 

	}



}
