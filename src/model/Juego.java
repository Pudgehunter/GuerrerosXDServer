package model;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.Session;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import model.TCPLauncher;

public class Juego {
	
	//Atributos
	
	//PAPPLET
	private PApplet app;
	
	//Pintar Muñecos
	private ArrayList<Personajes> aldeanos;
	private Personajes jugadorUno;
	private Personajes jugadorDos;
	private ArrayList<Anillo> anillo;
	
	//Matriz
	private int[][] matrizMapa;
	
	//Timer del juego y validar lo random de los muñecos
	private int segundos;
	private int timerOne;
	private int timerTotal = 1000;
	
	//Respawn Aldeanos
	private int respawnTime;
	
	//AnilloRespawnTime
	private int anilloXDTimeRespawn;
	
	//Tiempo de recarga
	private int recarga;
	
	//En el matriz = 2 es la ubicación del jugador 1 y matriz = 3 siendo la ubicación del jugador 2
	
	//Columnas y filas para el size de la matriz y que no se pase de ahi
	private int columnas,filas;
	
	//Matriz de los personajes
	private int playerOneMX, playerOneMY;
	private int playerTwoMX, playerTwoMY;
	
	//Matriz de los Aldeanos
	private int[] aldeanosX;
	private int[] aldeanosY;
	
	//Score!
	private int pJugadorUno, pJugadorDos;
	
	//Boolean Anillo
	private boolean anilloPowerOne, anilloPowerTwo;
	private boolean tiempoRecarga;
	
	//Anillo EveryWhere
	private ArrayList<AnilloPower> anilloDisparo;
	
	//MapaPintado
	private PImage mapa;
	
	//Jugadores Validar ganador
	private boolean ganoJugadorUno;
	private boolean ganoJugadorDos;
	
	
	//TCP LINKS
	private TCPLauncher tcplauncher;
	
	
	//CONSTRUCTOR
	public Juego(PApplet app) {
		this.app = app;
		//Cargar o llamar los dibujos
		aldeanos = new ArrayList<Personajes>();
		anillo = new ArrayList<>();
		mapa = app.loadImage("../img/mapa.png");
		
		//No se si sea necesario llamar el setup la matriz para que se entienda en main pero lo hare.
		matrizMapa = new int[][]{
				{0,0,0,0,0,0,0},
				{0,1,1,1,1,2,0},
				{0,1,0,1,0,1,0},
				{0,1,1,1,1,1,0},
				{0,1,0,1,0,1,0},
				{0,1,1,1,1,1,0},
				{0,1,1,1,1,1,0},
				{0,1,0,1,0,1,0},
				{0,1,1,1,1,1,0},
				{0,1,0,1,0,1,0},
				{0,1,1,1,1,3,0},
				{0,0,0,0,0,0,0},
		};
		/* Cada número en la matriz tiene una función entonces el 0 seria las paredes, el 1 es donde los muñecos
		 * se puedan mover, 2 y 3 son la ubicación de los personajes. El 4 que va aparecer luego son los aldeanos
		 * con sus respectivo lugar y el 5 seria el anillo.*/
		
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
					int posX = playerOneMX*100;
					int posY = playerOneMY*100;
					jugadorUno = new JugadorUno(app,posX,posY);
				}
				
				//Jugador 2
				if(matrizMapa[i][j] == 3) {
					playerTwoMX = i;
					playerTwoMY = j;
					int posX = playerTwoMX*100;
					int posY = playerTwoMY*100;
					jugadorDos= new JugadorDos(app,posX,posY);
				}
			}
		}
		
		//Validar el tiempo para que el juego funcione los randoms aldeanos y el anillo
		segundos = 0;
		respawnTime = 0;
		timerOne = app.millis();
		
		//Validar los score cada vez que recluta aldeanos
		pJugadorUno = 0;
		pJugadorDos = 0;
		
		//Validar el poder del anillo
		anilloPowerOne = false;
		anilloPowerTwo = false;
		tiempoRecarga = false;
		
		//Validar disparos del jugadorUno cuando tiene el anillo
		anilloDisparo = new ArrayList<>();
		
		//Validar ganador
		ganoJugadorUno = false;
		ganoJugadorDos = false;
		
		//Session...
		tcplauncher = TCPLauncher.getInstance();
		tcplauncher.setServidor(this);
		tcplauncher.start();
		
	}
	
	
	
	
	//METODOS
	
	public void dibujarMapaJuego() {
		
		//dibujar el mapa
		app.image(mapa,0,0);
		
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
				if(matrizMapa[i][j] == 4) {
					for (int alde = 0; alde < aldeanos.size(); alde++) {
						aldeanos.get(alde).dibujarPersonaje();
					}
				}
				if(matrizMapa[i][j] == 5) {
					for (int iAnillo = 0; iAnillo < anillo.size(); iAnillo++) {
						anillo.get(iAnillo).dibujarAnillo();
					}
				}
			}
		}
		
		//Dibujar el tiempo arriba y pues que vaya corriendo mientras.
		int contadorTiempo = app.millis() - timerOne;
		if(contadorTiempo > timerTotal) {
			//Tiempo que aparece en el juego
			segundos = segundos + 1;
			//El tiempo para que renazcan los aldeanos
			respawnTime = respawnTime + 1;
			//El tiempo para que aparezca el anillo
			anilloXDTimeRespawn = anilloXDTimeRespawn + 1;
			
			//Recarga
			if(tiempoRecarga == true) {
				recarga = recarga + 1;
			}
			
			timerOne = app.millis();
		}
		
		//validar cosass para que el jugador no dispare como loco hasta el final del juego
		if(recarga >= 10) {
			anilloPowerOne = false;
			anilloPowerTwo = false;
			recarga = 0;
			tiempoRecarga = false;
		}
		
		app.textSize(20);
		
		app.fill(0);
		app.text("Tiempo: "+segundos,app.width/2-50,50);
		
		//Puntajes
		app.text("Jugador 1: " + pJugadorUno,60,50);
		app.text("Jugador 2: " + pJugadorDos,app.width-200,50);
		
			//System.out.println(aldeanosX[0] + " " + aldeanosY[0] );

	}
	
	public void respawnAnilloXD() {
		if(anilloXDTimeRespawn >= 30) {
			int randomPositionX = (int) app.random(5,7);
			int randomPositionY = (int) app.random(1,6);
			
			matrizMapa[randomPositionX][randomPositionY] = 5;
			
			app.fill(50,50,50);
			int posX = randomPositionX * 100;
			int posY = randomPositionY * 100;
			anillo.add(new Anillo(posX, posY, app));
			anilloXDTimeRespawn = 0;
		}
	}

	
	public void validarPower() {
		if(matrizMapa[playerOneMX][playerOneMY] == 5) {
			anilloPowerOne = true;
			tiempoRecarga = true;
			
			for (int j = 0; j < anillo.size(); j++) {
				anillo.remove(j);
			}
			//para que el otro jugador aunque no vea la cosa lo recolecte... Hubo un bug sobre eso
			matrizMapa[playerOneMX][playerOneMY] = 1;
		}
		if(matrizMapa[playerTwoMX][playerTwoMY] == 5) {
			anilloPowerTwo = true;
			tiempoRecarga = true;
			
			for (int j = 0; j < anillo.size(); j++) {
				anillo.remove(j);
			}
			//para que el otro jugador aunque no vea la cosa lo recolecte... Hubo un bug sobre eso
			matrizMapa[playerTwoMX][playerTwoMY] = 1;
		}
		
	}
	
	public void anilloXDPower() {
		if(anilloPowerOne == true) {
		//Creo unas ellipse que van a molestar al jugador 2
			app.text("Jugador 1 tiene el poder",(app.width/2) - 150,app.height - 30);
			
			for (int i = 0; i < anilloDisparo.size(); i++) {
				anilloDisparo.get(i).dibujarPoder();
				if(jugadorDos.getPosX() < anilloDisparo.get(i).getPosX()) {
					anilloDisparo.get(i).setPosX(anilloDisparo.get(i).getPosX() - 10);
				}
				if(jugadorDos.getPosX() > anilloDisparo.get(i).getPosX()) {
					anilloDisparo.get(i).setPosX(anilloDisparo.get(i).getPosX() + 10);
				}
				if(jugadorDos.getPosY() < anilloDisparo.get(i).getPosY()) {
					anilloDisparo.get(i).setPosY(anilloDisparo.get(i).getPosY() - 10);
				}
				if(jugadorDos.getPosY() > anilloDisparo.get(i).getPosY()) {
					anilloDisparo.get(i).setPosY(anilloDisparo.get(i).getPosY() + 10);
				}
				
				//Validar resta de puntos hehe
				if(anilloDisparo.get(i).getPosX() == jugadorDos.getPosX() && anilloDisparo.get(i).getPosY() == jugadorDos.getPosY()) {
					pJugadorDos = pJugadorDos - 10;
					anilloDisparo.remove(i);
				}
			}
		}
		
		if(anilloPowerTwo == true) {
			
			app.text("Jugador 2 tiene el poder",(app.width/2) - 150,app.height - 30);
			
			for (int i = 0; i < anilloDisparo.size(); i++) {
				anilloDisparo.get(i).dibujarPoder();
				if(jugadorUno.getPosX() < anilloDisparo.get(i).getPosX()) {
					anilloDisparo.get(i).setPosX(anilloDisparo.get(i).getPosX() - 10);
				}
				if(jugadorUno.getPosX() > anilloDisparo.get(i).getPosX()) {
					anilloDisparo.get(i).setPosX(anilloDisparo.get(i).getPosX() + 10);
				}
				if(jugadorUno.getPosY() < anilloDisparo.get(i).getPosY()) {
					anilloDisparo.get(i).setPosY(anilloDisparo.get(i).getPosY() - 10);
				}
				if(jugadorUno.getPosY() > anilloDisparo.get(i).getPosY()) {
					anilloDisparo.get(i).setPosY(anilloDisparo.get(i).getPosY() + 10);
				}
				
				//Validar resta de puntos hehe
				if(anilloDisparo.get(i).getPosX() == jugadorUno.getPosX() && anilloDisparo.get(i).getPosY() == jugadorUno.getPosY()) {
					pJugadorUno = pJugadorUno - 10;
					anilloDisparo.remove(i);
				}
			}
		}
	}
	
	public void anilloXDPowerClick() {
		for (int i = 0; i < tcplauncher.getSessions().size(); i++) {
			Session session = tcplauncher.getSessions().get(0);
			Session session2 = tcplauncher.getSessions().get(1);
		if("atacar".equals(session.getMovimiento().getAtacar())){
			if(anilloPowerOne == true) {
				anilloDisparo.add(new AnilloPower(playerOneMX*100,playerOneMY*100,app,playerOneMX,playerOneMY));
			}
		}
		if("atacar".equals(session.getMovimiento().getAtacar())) {
			if(anilloPowerTwo == true) {
				anilloDisparo.add(new AnilloPower(playerTwoMX*100,playerTwoMY*100,app,playerTwoMX,playerTwoMY));
				}
			}
		}
	}
	
	
	public void moverMuñeco() {
		for (int i = 0; i < tcplauncher.getSessions().size(); i++) {
			Session session = tcplauncher.getSessions().get(0);
			Session session2 = tcplauncher.getSessions().get(1);
		if("arriba".equals(session.getMovimiento().getAtacar()) && matrizMapa[playerOneMX][playerOneMY - 1] != 0) {
			playerOneMY = playerOneMY - 1;
			jugadorUno.setPosY(jugadorUno.getPosY() - session.getMovimiento().getPosY());
			//jugadorUno.moverArriba();
			validarAldeanosJugadorUno();
			validarPower();
		}
		if("abajo".equals(session.getMovimiento().getAtacar()) && matrizMapa[playerOneMX][playerOneMY + 1] != 0) {
			playerOneMY = playerOneMY + 1;
			jugadorUno.setPosY(jugadorUno.getPosY() + session.getMovimiento().getPosY());
			//jugadorUno.moverAbajo();
			validarAldeanosJugadorUno();
			validarPower();
			}
		if("derecha".equals(session.getMovimiento().getAtacar()) && matrizMapa[playerOneMX + 1][playerOneMY] != 0) {	
			playerOneMX = playerOneMX + 1;
			jugadorUno.moverDerecha();
			validarAldeanosJugadorUno();
			validarPower();
			}
		if("izquierda".equals(session.getMovimiento().getAtacar()) && matrizMapa[playerOneMX - 1][playerOneMY] != 0) {
			playerOneMX = playerOneMX - 1;
			jugadorUno.moverIzquierda();
			validarAldeanosJugadorUno();
			validarPower();
			}
		if("arriba".equals(session2.getMovimiento().getAtacar()) && matrizMapa[playerTwoMX][playerTwoMY - 1] != 0) {
			playerTwoMY = playerTwoMY - 1;
			jugadorDos.moverArriba();
			validarAldeanosJugadorDos();
			validarPower();
			}
		if("abajo".equals(session2.getMovimiento().getAtacar()) && matrizMapa[playerTwoMX][playerTwoMY + 1] != 0) {
			playerTwoMY = playerTwoMY + 1;
			jugadorDos.moverAbajo();
			validarAldeanosJugadorDos();
			validarPower();
			}
		if("derecha".equals(session2.getMovimiento().getAtacar()) && matrizMapa[playerTwoMX + 1][playerTwoMY] != 0) {
			playerTwoMX = playerTwoMX + 1;
			jugadorDos.moverDerecha();
			validarAldeanosJugadorDos();
			validarPower();
		}
		if("izquierda".equals(session2.getMovimiento().getAtacar()) && matrizMapa[playerTwoMX - 1][playerTwoMY] != 0) {
			playerTwoMX = playerTwoMX - 1;
			jugadorDos.moverIzquierda();
			validarAldeanosJugadorDos();
			validarPower();
			}
		}
	}
	
	//Funciones de validar aldeanos reclutados por los jugadores.
	public void validarAldeanosJugadorUno() {
		if(matrizMapa[playerOneMX][playerOneMY] == 4) {
			for (int j = 0; j < aldeanos.size(); j++) {
				if(jugadorUno.getPosX() == aldeanos.get(j).getPosX() && jugadorUno.getPosY() == aldeanos.get(j).getPosY()) {
					aldeanos.remove(j);	
				}
			}
			matrizMapa[playerOneMX][playerOneMY] = 1;
			pJugadorUno = pJugadorUno + 100;
		}
	}
	public void validarAldeanosJugadorDos() {
		if(matrizMapa[playerTwoMX][playerTwoMY] == 4) {
			for (int j = 0; j < aldeanos.size(); j++) {
				if(jugadorDos.getPosX() == aldeanos.get(j).getPosX() && jugadorDos.getPosY() == aldeanos.get(j).getPosY()) {
					aldeanos.remove(j);	
				}
			}
			matrizMapa[playerTwoMX][playerTwoMY] = 1;
			pJugadorDos = pJugadorDos + 100;
		}
	}
	
	//Validar jugadores
	public void validarWinner() {
		if(segundos >= 100) {
			segundos = 0;
			
			//Jugador Uno Gano
			if(pJugadorUno > pJugadorDos) {
				ganoJugadorUno = true;
			}
			
			//Jugador Dos Gano
			if(pJugadorDos > pJugadorUno) {
				ganoJugadorDos = true;
			}
		}
	}
	
	
	public void randomCivilAppears() {
		if(respawnTime >= 10) {
			new Thread(
					() -> {
						int MatrizRandomX = (int) app.random(1,11);
						int MatrizRandomY = (int) app.random(1,6);
						System.out.println(MatrizRandomX + " " + MatrizRandomY);
								if(matrizMapa[MatrizRandomX][MatrizRandomY] == 1) {
									int respawnX = 100 * MatrizRandomX;
									int respawnY = 100 * MatrizRandomY;
									
									//se agrega el aldeano
									aldeanos.add(new Aldeanos(app,respawnX,respawnY));
									matrizMapa[MatrizRandomX][MatrizRandomY] = 4;
									
									app.noLoop();
								} else if(matrizMapa[MatrizRandomX][MatrizRandomY] != 1) {
									MatrizRandomX = (int) app.random(1,11);
									MatrizRandomY = (int) app.random(1,6);
									//pensaba que creando loop se iba a repetir la cosa, pero como que toca manual para que se cree otra.
									
									if(matrizMapa[MatrizRandomX][MatrizRandomY] == 1) {
										int respawnX = 100 * MatrizRandomX;
										int respawnY = 100 * MatrizRandomY;
										
										//se agrega el aldeano
										aldeanos.add(new Aldeanos(app,respawnX,respawnY));
										matrizMapa[MatrizRandomX][MatrizRandomY] = 4;
										app.noLoop();
									}
								}
								app.loop();
					}
					).start();
					respawnTime = 0;	
				}
		}
	
	//Metodos de Getter && Setters
	public boolean isGanoJugadorUno() {
		return ganoJugadorUno;
	}
	public void setGanoJugadorUno(boolean ganoJugadorUno) {
		this.ganoJugadorUno = ganoJugadorUno;
	}
	public boolean isGanoJugadorDos() {
		return ganoJugadorDos;
	}
	public void setGanoJugadorDos(boolean ganoJugadorDos) {
		this.ganoJugadorDos = ganoJugadorDos;
	}
	
	public void mensaje(Session session, String mensaje) {
		System.out.println("Mensaje recibido de: " + mensaje);
		Gson gson = new Gson();
		Movimiento jugadorReader = gson.fromJson(mensaje, Movimiento.class);
		//Aca tengo que hacer todo lo que entra en el while, pues los movimientos de los personajes...

		session.setMovimiento(jugadorReader);
	}
	
}
