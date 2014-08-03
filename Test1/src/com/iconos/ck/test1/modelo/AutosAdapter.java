package com.iconos.ck.test1.modelo;

import com.androidquery.AQuery;
import com.iconos.ck.test1.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class AutosAdapter extends ArrayAdapter<Auto> {

	public AutosAdapter(Context context) {
		super(context, R.layout.listactivity_row, R.id.tv_Nombre);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = super.getView(position, convertView, parent);
		
		AQuery aq = new AQuery(view);
		Auto auto = getItem(position);
		
		aq.id(R.id.tv_Nombre).text(auto.marca + " " + auto.modelo);
		aq.id(R.id.iv_ImagenAuto).image(auto.image);
		
		return view;
	}

	
}
