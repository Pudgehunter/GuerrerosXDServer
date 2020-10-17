package model;

import processing.core.PApplet;

public class JugadorUno extends Personajes{
	
	public JugadorUno(PApplet app,int posX,int posY) {
		super(app,posX,posY);
	}
	
	public void dibujarPersonaje() {
		app.image(personaje[2],100,50,50,100);
	}
	
	
}
