package co.edu.icesi.tcp.server;

public class EjecutableServer {
	
	static ComunicacionServer comServer;
	
	public static void main(String[] args) {
		comServer = new ComunicacionServer();
		comServer.start();
		while(true){}
	}
}
