package controler;

import model.Logica;
import processing.core.PApplet;

public class Control {
	
	PApplet app;
	Logica logica;
	
	public Control(PApplet app) {
		this.app = app;
		logica = new Logica(app);
	}
	
	public void dibujarMapaControl() {
		logica.logicGame();
	}
	
	public void click() {
		logica.mouseClicked();
	}

}
