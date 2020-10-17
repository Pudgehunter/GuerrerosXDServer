package model;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Personajes {

	protected PImage[] personaje;
	protected PApplet app;
	
	//Posiciones
	protected int posX, posY;
	
	//Posiciones en la matriz
	protected int matrixX, matrixY;
	
	public Personajes(PApplet app, int posX, int posY) {
		this.app = app;
		this.personaje = new PImage[4];
		loadImagePersonajes();
	}
	
	public void loadImagePersonajes() {
		this.personaje[0] = app.loadImage("img/Aldeano.png");
		this.personaje[1] = app.loadImage("img/Aldeana.png");
		this.personaje[2] = app.loadImage("img/soldadoUno.png");
		this.personaje[3] = app.loadImage("img/soldadoDos.png");
	}
	
	public abstract void dibujarPersonaje();
	
	public void moverArriba() {
		//posY = posY - 50;
		//matrizY = matrizY - 1;
	}
	public void moverAbajo() {
		//posY = posY + 50;
		//matrizY = matrizY + 1;
	}
	public void moverIzquierda() {
		//posX = posX - 50;
		//matrizX = matrizX - 1;
	}
	public void moverDerecha() {
		//posX = posX + 50;
		//matrizX = matrizX + 1;
	}
	
	public void poderAnillo() {
		
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

	public int getMatrixX() {
		return matrixX;
	}

	public void setMatrixX(int matrixX) {
		this.matrixX = matrixX;
	}

	public int getMatrixY() {
		return matrixY;
	}

	public void setMatrixY(int matrixY) {
		this.matrixY = matrixY;
	}
	
	
	
	
}
