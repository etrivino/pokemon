package com.proyectopokemon.logicapokemon.accesoadatos.modelosmanejadores;

public class RespuestaInfoPokemon {
	
	private int base_experience;
	private int height;
	private int id;
	private String name;
	private Sprite sprites;
	
	public RespuestaInfoPokemon() {
		
	}
	
	public int getBase_experience() {
		return base_experience;
	}
	public void setBase_experience(int base_experience) {
		this.base_experience = base_experience;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sprite getSprites() {
		return sprites;
	}
	public void setSprites(Sprite sprites) {
		this.sprites = sprites;
	}
	public String toString() {
		return name+" -> "+id;
	}
}
