package co.edu.icesi.tcp.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.icesi.tcp.common.Mensaje;

public class ComunicacionServer extends Thread {

	private final String TAG = "SERVIDOR: ";
	private static final int PUERTO = 5000;
	private ServerSocket ss;
	private Socket s;

	public ComunicacionServer() {
		try {
			System.out.println(TAG + "Iniciando servidor");
			ss = new ServerSocket(PUERTO);
			System.out.println(
					TAG + "Servidor activo, escuchando en " + ss.getInetAddress().toString() + ":" + ss.getLocalPort());
			s = ss.accept();
			System.out.println(TAG + "Se conectó un cliente desde "+s.getInetAddress().toString()+":"+s.getPort());
			// enviar el primer número
			enviarAleatorio();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			Object dato = recibir();
			if (dato != null) {
				enviarAleatorio();
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Object recibir() {
		try {
			ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());
			Object recibido = entrada.readObject();
			System.out.println(TAG + "recibido: " + recibido.toString());
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
			System.out.println(TAG + "Se envia el elemento: " + ob.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarAleatorio(){
		double random = Math.random() * 500;
		Mensaje m = new Mensaje();
		m.setValor((int) random);
		enviar(m);
	}

}
