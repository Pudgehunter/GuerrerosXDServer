package view;

import controler.Control;
import processing.core.PApplet;

public class Main extends PApplet {
	
	Control controler;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("view.Main");
	}
	
	public void settings() {
		size(1200,700);
	}
	
	public void setup() {
		controler = new Control(this);
	}
	
	public void draw() {
		background(255);
		controler.dibujarMapaControl();
		text(mouseX + " : " + mouseY,mouseX,mouseY);
	}
	
	public void keyPressed() {
		controler.mover();
	}
	
	public void mouseClicked() {
		controler.click();
	}

}
