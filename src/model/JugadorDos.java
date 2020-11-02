package model;

import processing.core.PApplet;

public class JugadorDos extends Personajes{
	
	public JugadorDos(PApplet app,int posX, int posY) {
		super(app,posX,posY);
	}
	
	public void dibujarPersonaje() {
		app.image(personaje[3],posX,posY,75,100);
	}
	
	
}