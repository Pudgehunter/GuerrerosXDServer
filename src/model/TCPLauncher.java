package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.Juego;
import model.Session;
import model.TCPLauncher;

public class TCPLauncher extends Thread{
	
	private static TCPLauncher onlyInstance;
	
	
	//Singleton ()
	public static TCPLauncher getInstance() {
		if(onlyInstance == null) {
			onlyInstance = new TCPLauncher();
		}
		return onlyInstance;
	}
	
	private TCPLauncher() {}
	
	//Singleton
	
	//Atributos Globales 
	private Juego juego;
	private ServerSocket server;
	private ArrayList<Session> sessionArrayList;
	
	//Metodos
	
	//1.Metodo de session
	public void setServidor(Juego juego) {
		this.juego = juego;
	}
	
	public void run(){
		try {
			//1. Iniciar el servidor y esperar una conexión
			// tener en cuenta session primero
			sessionArrayList = new ArrayList<Session>();
			server = new ServerSocket(5000);
			System.out.println("Esperando conexión");
			
			while(true) {
				System.out.println("Esperando que se conecten...");
				Socket socket = server.accept();
				
				//Una vez conectados empezamos con los sessions
				Session session = new Session(socket);
				session.setJuego(juego);
				session.start();
				
				sessionArrayList.add(session);
				
				//3. Conectados!
				System.out.println("Cliente conectados");
	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Session> getSessions() {
		return this.sessionArrayList;
	}

}
