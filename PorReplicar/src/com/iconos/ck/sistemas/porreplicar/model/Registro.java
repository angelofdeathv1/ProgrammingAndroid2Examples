package com.iconos.ck.sistemas.porreplicar.model;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

@Entity
public class Registro {
	
	@Id
	private long id;
	private String nombreTienda;
	private int noRegistros;
	private String imagen;
	private String nombreEstacion;
	
	public static Registro crearRegistro(String nombreTienda, int noRegistros, String imagen, String nombreEstacion){
		Registro reg = new Registro();
		reg.nombreTienda = nombreTienda;
		reg.noRegistros = noRegistros;
		reg.imagen = imagen;
		reg.nombreEstacion = nombreEstacion;
		
		return reg;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public int getNoRegistros() {
		return noRegistros;
	}

	public void setNoRegistros(int noRegistros) {
		this.noRegistros = noRegistros;
	}

	

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombreEstacion() {
		return nombreEstacion;
	}

	public void setNombreEstacion(String nombreEstacion) {
		this.nombreEstacion = nombreEstacion;
	}

}