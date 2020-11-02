package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Anillo {
	
	private PApplet app;
	private PImage anilloImage;
	private int posX,posY;
	
	public Anillo(int posX,int posY, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.app = app;
		this.anilloImage = app.loadImage("../img/anillo.png");
	}
	
	public void dibujarAnillo() {
		//app.rect(posX,posY,50,50);
		app.image(anilloImage, posX, posY,75,75);
	}

}
