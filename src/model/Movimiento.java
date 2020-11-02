package model;

public class Movimiento {

	//Posiciones
	protected int posX, posY;
	protected String atacar;
	
	public Movimiento() {}
	
	public Movimiento(String atacar, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.atacar = atacar;
	}

	//Metodo
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

	public String getAtacar() {
		return atacar;
	}

	public void setAtacar(String atacar) {
		this.atacar = atacar;
	}
	
	
}
