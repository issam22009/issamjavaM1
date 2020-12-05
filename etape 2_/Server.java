

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(1234);
			System.out.println("Bienvenue sur votre serveur");
			while(true)
			{
				Socket s=ss.accept();
				System.out.println("un client a rejoint le serveur"); 
				new Traitement(s).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	class Traitement extends Thread{
		private Socket sock;
		
		public Traitement(Socket sock) {
			super();
			this.sock=sock;
		}
		
		public  void run() {
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server().start();

	}
	
}
