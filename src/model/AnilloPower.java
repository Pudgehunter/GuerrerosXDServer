package model;

import processing.core.PApplet;
import processing.core.PImage;

public class AnilloPower {
	
	private PApplet app;
	private int posX,posY;
	private int mX,mY;
	private PImage disparoA;
	
	public AnilloPower(int posX,int posY, PApplet app, int mX, int mY) {
		this.posX = posX;
		this.posY = posY;
		this.app = app;
		//Aldeanos atacando
		this.disparoA = app.loadImage("../img/attack.png");
	}
	
	public void dibujarPoder() {
		//imagen
		app.image(disparoA,posX,posY,100,75);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getmX() {
		return mX;
	}

	public void setmX(int mX) {
		this.mX = mX;
	}

	public int getmY() {
		return mY;
	}

	public void setmY(int mY) {
		this.mY = mY;
	}
	
	
	

}
