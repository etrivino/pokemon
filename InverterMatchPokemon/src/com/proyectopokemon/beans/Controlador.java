package com.proyectopokemon.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "bean")
@SessionScoped
public class Controlador {
	
	private String jsonCad = "qeweqweqweq";

	public String getJsonCad() {
		return jsonCad;
	}

	public void setJsonCad(String jsonCad) {
		this.jsonCad = jsonCad;
	}
	
}
