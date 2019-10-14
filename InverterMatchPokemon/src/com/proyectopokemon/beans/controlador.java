package com.proyectopokemon.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class controlador {
	
	private String jsonCad;

	public String getJsonCad() {
		return jsonCad;
	}

	public void setJsonCad(String jsonCad) {
		this.jsonCad = jsonCad;
	}
	
}
