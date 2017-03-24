package co.edu.icesi.tcp.client;

import java.util.Observable;
import java.util.Observer;

import co.edu.icesi.tcp.common.Mensaje;
import processing.core.PApplet;

public class Logica implements Observer{
	private PApplet app;
	private ComunicacionCliente comCliente;
	private int posX;
	private int posY;
	private boolean comenzar;
	
	public Logica(PApplet app) {
		this.app = app;
		posX=0;
		comenzar = false;
		comCliente = new ComunicacionCliente();
		comCliente.addObserver(this);
		Thread hilo = new Thread(comCliente);
		hilo.start();
		
	}

	public void pintar() {
		app.background(255);
		if(comenzar){
			app.fill(255,0,0);
			app.ellipse(posX, posY, 50, 50);
			posX+=10;
			if(posX>=app.width){
				Mensaje m = new Mensaje();
				m.setValor(0);
				comCliente.enviar(m);
				comenzar=false;
			}
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Un observador paso un mensaje");
		System.out.println(o.toString());
		System.out.println(arg.toString());
		
		if(arg instanceof Mensaje){
			Mensaje m = (Mensaje) arg;
			posY=m.getValor();
			posX=0;
			comenzar=true;
		}
		
	}
	
	

}
