package com.iconos.ck.test1.modelo;

import org.json.JSONException;
import org.json.JSONObject;

public class Auto {
	
	public String marca;
	public String modelo;
	public int anio;
	public String image;
	
	public static Auto crearAuto(JSONObject obj) throws JSONException{
		Auto auto = new Auto();
		auto.marca = obj.getString("marca");
		auto.modelo = obj.getString("modelo");
		auto.anio = obj.getInt("anio");
		auto.image = obj.getString("image");
		return auto;
	}

}
