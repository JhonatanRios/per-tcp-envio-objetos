package co.edu.icesi.tcp.common;
import java.io.Serializable;


public class Mensaje implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4449273319593903004L;
	private int valor;
	
	public Mensaje() {
		valor = 0;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
	
	@Override
	public String toString() {
		return "valor: "+valor;
	}
}
