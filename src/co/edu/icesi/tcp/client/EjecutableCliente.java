package co.edu.icesi.tcp.client;


import processing.core.PApplet;

public class EjecutableCliente extends PApplet{
	
	
	private Logica log;
	
	public static void main(String[] args) {
		PApplet.main("co.edu.icesi.tcp.client.EjecutableCliente");
		
	}
	
	public void settings(){
		size(500,500);
	}
	
	public void setup(){
		log = new Logica(this);
	}
	
	public void draw(){
		log.pintar();
	}
}
