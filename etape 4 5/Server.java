//package java.Server.Client;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Server extends Thread{
	JPanel panel;
	
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
	class InterGraphServer extends JFrame{
		ContentServer pan;
		public InterGraphServer()
		{
			super();
			proprietiesServer();
		}
		public void proprietiesServer() {
		JFrame frame = new JFrame("Server");

		frame.setSize(800,800);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan = new ContentServer();
		this.setContentPane(pan);
		}
		
		
	}
	class ContentServer extends JPanel{
		private JLabel etiq;
		
		
		public ContentServer() {
			super();
			proprietiesetiq();
			
		}
		private void proprietiesetiq() {
			etiq = new JLabel();
			this.etiq.setBounds(10,20,350,20);
			this.etiq.setText("Bienvenue sur Votre programme Serveur");
			this.add(etiq);
		}
		
	}
	public static void main(String[] args) {
		new Server().start();
	}
}

