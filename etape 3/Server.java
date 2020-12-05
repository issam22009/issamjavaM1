//package java.Server.Client;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread implements Serializable{
	
	private List<Object> sent = new ArrayList<Object>();
	private List<Object> Recv = new ArrayList<Object>();
	
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(123);
			System.out.println("Bienvenue sur votre serveur"); 
			while(true) {
				Socket s = ss.accept();
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
		
		public synchronized void run() {
			try {
				
				OutputStream os = sock.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				
				
				// l'objet dont on connait pas le type
				Class c = Object.class;
				Class c2 = new Object().getClass();
			    sent.add(c2);
				
				
				oos.writeObject(c2);
				System.out.println("un objet a été envoyé "); 
				
				
				InputStream is = sock.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				
				Object o = (Object) ois.readObject();
				System.out.println("un objet a été reçu "); 
				 Recv.add(o);
				 System.out.println("un objet est ajouté à la liste.  "); 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			//} catch (InstantiationException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {
		new Server().start();
	}
}

