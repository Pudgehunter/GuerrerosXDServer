package model;

import processing.core.PApplet;

public class Aldeanos extends Personajes {

	public Aldeanos(PApplet app,int posX, int posY) {
		super(app,posX,posY);
	}
	
	public void dibujarPersonaje() {
		app.image(personaje[0],posX,posY,60,85);
		app.image(personaje[1],posX+20,posY+20,60,85);
	}
}
