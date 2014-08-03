package com.iconos.ck.sistemas.porreplicar.model;


import com.androidquery.AQuery;
import com.iconos.ck.sistemas.porreplicar.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class RenglonAdapter extends ArrayAdapter<Registro>{
	
	public RenglonAdapter(Context context) {
		super(context, R.layout.activity_lista_archivo_renglon, R.id.listaArchivoRenglon_tv_nombreTienda);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = super.getView(position, convertView, parent);
		
		AQuery aq = new AQuery(view);
		Registro reg = getItem(position);
		aq.id(R.id.listaArchivoRenglon_tv_nombreTienda).text(reg.getNombreTienda());
		aq.id(R.id.listaArchivoRenglon_tv_noRegistros).text(reg.getNoRegistros()+"");
		//aq.id(R.id.listaArchivoRenglon_tv_logo).image(R.drawable.ic_launcher);
		aq.id(R.id.listaArchivoRenglon_tv_nombreTienda).text(reg.getNombreEstacion());
		
		return view;
	}

}
