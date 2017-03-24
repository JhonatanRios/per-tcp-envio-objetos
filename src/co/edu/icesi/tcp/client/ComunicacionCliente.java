package co.edu.icesi.tcp.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;

import co.edu.icesi.tcp.common.Mensaje;


public class ComunicacionCliente extends Observable implements Runnable {
	
	private final String TAG = "CLIENTE: ";
	private static final int PUERTO = 5000;	
	private static final String DIRECCION_SERVIDOR = "127.0.0.1";
	private Socket s;
	
	public ComunicacionCliente() {
		try {			
			InetAddress dirServidor = InetAddress.getByName(DIRECCION_SERVIDOR);
			System.out.println(TAG+"Intentando conectar con el servidor");
			s = new Socket(dirServidor, PUERTO);
			System.out.println(TAG+"servidor encontrado");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			Object dato = recibir();
			if(dato!=null){
				setChanged();
				notifyObservers(dato);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Object recibir() {		
		try {
			ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());
			Object recibido = entrada.readObject();
			System.out.println(TAG+"recibido: " + recibido.toString());
			return recibido;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void enviar(Object ob) {
		try {
			ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream());
			salida.writeObject(ob);
			System.out.println(TAG+"Se envia el elemento: " + ob.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	
	
	
}
