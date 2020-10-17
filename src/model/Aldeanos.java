package model;

import processing.core.PApplet;

public class Aldeanos extends Personajes {

	public Aldeanos(PApplet app,int posX, int posY) {
		super(app,posX,posY);
	}
	
	public void dibujarPersonaje() {
		app.image(personaje[0],0,0,50,50);
		app.image(personaje[1],50,0,50,50);
	}
}
