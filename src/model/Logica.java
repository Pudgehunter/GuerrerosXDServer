package model;

import processing.core.PApplet;

public class Logica {
	
	private PApplet app;
	private Juego juegos;
	private int pantalla;
	
	public Logica(PApplet app) {
		this.app = app;
		juegos = new Juego(app);
		this.pantalla = 2;
	}
	
	public void logicGame() {
		switch(pantalla) {
		// Pantalla Inicial
		case 0:
			System.out.println("si funciono");
			break;
			//Pantalla Intro
		case 1:
			System.out.println("si funciono");
			break;
			//Pantalla Juego
		case 2:
			juegos.dibujarMapaJuego();
			break;
		}
	}
	
	public void logicGameMouseClick() {
		//Se puede manejarlo en el mouse hasta el juego donde se valida los celulares
		switch(pantalla) {
			//pantalla Inicial
		case 0:
			
			break;
			//Pantalla Intro
		case 1:
			
			break;
			//Pantalla Juego
		case 2:
			//La mayoria de las cosas ya lo manipula los celulares entonces no es necesario poner datos aqui
			break;
		}
	}

}
