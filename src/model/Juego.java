package model;

import java.util.ArrayList;

import processing.core.PApplet;

public class Juego {
	
	private PApplet app;
	private ArrayList<Personajes> aldeanos;
	private Personajes jugadorUno, jugadorDos;
	private Anillo anillo;
	private int[][] matrizMapa = {
			{0,0,0,0,0,0,0},
			{0,2,1,1,1,1,0},
			{0,1,0,1,0,1,0},
			{0,1,1,1,1,1,0},
			{0,1,0,1,0,1,0},
			{0,1,1,1,1,1,0},
			{0,1,1,1,1,1,0},
			{0,1,0,1,0,1,0},
			{0,1,1,1,1,1,0},
			{0,1,0,1,0,1,0},
			{0,3,1,1,1,1,0},
			{0,0,0,0,0,0,0},
	};
	//En el matriz = 2 es la ubicación del jugador 1 y matriz = 3 siendo la ubicación del jugador 2
	
	//Columnas y filas para el size de la matriz y que no se pase de ahi
	private int columnas,filas;
	
	//Matriz de los personajes
	private int playerOneMX, playerOneMY, playerTwoMX, playerTwoMY;
	
	public Juego(PApplet app) {
		this.app = app;
		//Cargar o llamar los dibujos
		aldeanos = new ArrayList<Personajes>();
		
		
		anillo = new Anillo(app);
		
		//tamaño de la matriz
		//columnas es X
		columnas = 12;
		//filas es Y
		filas = 7;
		
		//Visualizar el mapa
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				
				//Jugador 1
				
				if(matrizMapa[i][j] == 2) {
					playerOneMX = i;
					playerOneMY = j;
					//jugadorUno.setMatrixX(playerOneMX);
					//jugadorUno.setMatrixY(playerOneMY);
					int posX = playerOneMX*50+25;
					int posY = playerOneMY*50+25;
					jugadorUno = new JugadorUno(app,posX,posY);
				}
				
				//Jugador 2
				
				if(matrizMapa[i][j] == 3) {
					playerTwoMX = i;
					playerTwoMY = j;
					//jugadorDos.setMatrixX(playerTwoMX);
					//jugadorDos.setMatrixY(playerTwoMY);
					int posX = playerOneMX*50+25;
					int posY = playerOneMY*50+25;
					jugadorDos= new JugadorDos(app,posX,posY);
				}
				
			}
		}
	}
	
	public void dibujarMapaJuego() {
		//dibujar los aldeanos que salen randoms en el mapa
		//aldeanos.add(new Aldeanos(app));
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				if(matrizMapa[i][j] == 2) {
					jugadorUno.dibujarPersonaje();
				}
				if(matrizMapa[i][j] == 3) {
					jugadorDos.dibujarPersonaje();
				}
			}
		}
	}
	
	
}
