package model;

import com.google.gson.Gson;

import model.TCPLauncher;
import processing.core.PApplet;
import processing.core.PImage;

public class Logica {
	
	
	private PApplet app;
	private Juego juegos;
	private int pantalla;
	private PImage menu;
	private PImage[] instrucciones;
	private PImage[] jugabilidad;
	private PImage[] conectar;
	private int conectarPantalla;
	private int instruccionesPantalla;
	
	public Logica(PApplet app) {
		this.app = app;
		juegos = new Juego(app);
		this.pantalla = 0;
		
		//Exportar imagenes xd
		menu = app.loadImage("../img/Pantallas/menu.png");
		
		//Instrucciones son 2
		instrucciones = new PImage[2];
		instrucciones[0] = app.loadImage("../img/Pantallas/instrucciones.png");
		instrucciones[1] = app.loadImage("../img/Pantallas/instrucciones2.png");
		
		//jugabilidad son 2
		jugabilidad = new PImage[2];
		jugabilidad[0] = app.loadImage("../img/Pantallas/jugabilidad.png");
		jugabilidad[1] = app.loadImage("../img/Pantallas/jugabilidad2.png");
		
		//conectar son 3
		conectar = new PImage[3];
		conectar[0] = app.loadImage("../img/Pantallas/conectar.png");
		conectar[1] = app.loadImage("../img/Pantallas/conectar2.png");
		conectar[2] = app.loadImage("../img/Pantallas/conectar3.png");
		
		//pantallas iniciales
		instruccionesPantalla = 0;
		conectarPantalla = 0;
		
		
		
	}
	
	public void logicGame() {
		switch(pantalla) {
		// Pantalla Inicial
		case 0:
			app.image(menu,0,0);
			break;
			//Pantalla Conexiones
		case 1:
			
			//Conexiones interacciones
			switch(conectarPantalla) {
			case 0:
				app.image(conectar[0], 0, 0);
				break;
			case 1:
				app.image(conectar[1], 0, 0);
				break;
			case 2:
				app.image(conectar[2], 0, 0);
				break;
			}
			break;
			
			
			//Pantalla Intro
		case 2:
			
			
			switch(instruccionesPantalla) {
			case 0:
				app.image(instrucciones[0], 0, 0);
				if(app.mouseX > 1074 && app.mouseX< 1135 && app.mouseY > 634 && app.mouseY < 676) {
					app.image(instrucciones[1], 0, 0);
				}
				break;
			case 1:
				app.image(jugabilidad[0], 0, 0);
				if(app.mouseX > 1094 && app.mouseX< 1165 && app.mouseY > 634 && app.mouseY < 676) {
					app.image(jugabilidad[1], 0, 0);
				}
				break;
			}
			//ojala funcione las conexiones
			break;
		case 3:
			juegos.dibujarMapaJuego();
			juegos.randomCivilAppears();
			juegos.respawnAnilloXD();
			juegos.anilloXDPower();
			juegos.validarWinner();
			juegos.moverMuñeco();
			if(juegos.isGanoJugadorUno() == true) {
				pantalla = 4;
			}
			if(juegos.isGanoJugadorDos() == true) {
				pantalla = 4;
			}
			break;
		case 4:
			app.background(0);
			if(juegos.isGanoJugadorUno() == true) {
				app.text("ganador jugador Uno!!!",250,250);
			}
			if(juegos.isGanoJugadorDos() == true) {
				app.text("ganador jugador Dos!!!",250,250);
			}
			break;
		}
	}
	
	public void logicInteractive() {
		//Se puede manejarlo en el mouse hasta el juego donde se valida los celulares
		switch(pantalla) {
			//pantalla Inicial
		case 0:
			break;
			//Pantalla Conexiones
		case 1:
			
			break;
		case 2:
			//pantalla Intro
			break;
			//Pantalla Juego
		case 3:
			//La mayoria de las cosas ya lo manipula los celulares entonces no es necesario poner datos aqui
			
			break;
		case 4:
			
			break;
		}
	}
	
	public void mouseClicked() {
		switch(pantalla) {
		case 0:
			if(app.mouseX > 552 && app.mouseX< 648 && app.mouseY > 396 && app.mouseY < 441) {
				pantalla = 3;
			}
			if(app.mouseX > 487 && app.mouseX< 712 && app.mouseY > 490 && app.mouseY < 530) {
				pantalla = 2;
			}
			break;
		case 1:
			
			break;
		case 2:
			
			switch(instruccionesPantalla) {
			case 0:
				if(app.mouseX > 1074 && app.mouseX< 1135 && app.mouseY > 634 && app.mouseY < 676) {
					instruccionesPantalla = 1;
				}
				break;
			case 1:
				if(app.mouseX > 1094 && app.mouseX< 1165 && app.mouseY > 634 && app.mouseY < 676) {
					pantalla = 0;
				}
				break;
			}
			
			break;
		case 3:
			juegos.anilloXDPowerClick();
			break;
		case 4:
			
			break;
		}
	}
	

}
