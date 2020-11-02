package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Session extends Thread{
	
	private Movimiento movimiento;
	private BufferedWriter writer;
	private Socket socket;
	private Juego juego;
	
	
	public Session(Socket socket) {
		this.socket = socket;
		movimiento = new Movimiento("",0,0);
	}
	
	
	@Override
	public void run() {
		try {
			//Los reader y writers... (aunque los writer no sea necesario)
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out);	
			writer = new BufferedWriter(osw);
			
			//Para que lo recorra siempre
			while(true) {
				System.out.println("Esperando mensaje...");
				String lastMessage = reader.readLine();
				juego.mensaje(this, lastMessage);
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	
	public void enviar(String msg) {
		new Thread(
				
				()->{
					try {
						writer.write(msg + "\n");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
		).start();
		
	}
	//Metodos de getter y setters

	public BufferedWriter getWriter() {
		return writer;
	}


	public Movimiento getMovimiento() {
		return movimiento;
	}


	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}


	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}


	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public Juego getJuego() {
		return juego;
	}


	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
}
