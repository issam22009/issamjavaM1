//package java.Server.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Client {
	private static String rep;
	JFrame frame;

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			Socket s = new Socket("localhost",123);
			System.out.println("Bienvenue sur votre application client : "); 

			InputStream is = s.getInputStream();
			InputStreamReader isr= new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			ObjectInputStream ois= new ObjectInputStream(is);
			
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			Scanner scanner = new Scanner(System.in);
			
			
			try {
				Class P=(Class) ois.readObject();
				System.out.println("Vous avez bien reçu l'objet.");
				Method[] method = P.getMethods();
				Object o = null;
				String rep;
				long temp;
				
				for(Method m: method)
				{
					String mname = m.getName();
			 		Type[] pType = m.getGenericParameterTypes();
			 		Object[] entrees = new Object[pType.length];
					if(pType!=null)
						{
							for(int i =0;i<pType.length;i++)
							{
								System.out.println("Entrer une donnée de type "+ pType[i]);
								if (pType[i].equals(String.class)) {
									rep = scanner.next();
									entrees[i]=rep;
					            } else if (pType[i].equals(int.class)) {
					            	temp = scanner.nextInt();
					            	entrees[i]=temp;
					            } else if (pType[i].equals(long.class))
					            {
					            	temp = scanner.nextLong();
									entrees[i]=temp;
					            }
					            									
							}
						 m.setAccessible(true);
					     o = m.invoke(  entrees);
						
						}
				}
				oos.writeObject(o);
				System.out.println("Vous avez bien envoyé l'objet.");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class InterGraphClient extends JFrame{
		ContentClient pan;
		public InterGraphClient()
		{
			super();
			proprietiesClient();
		}
		public void proprietiesClient() {
		JFrame frame = new JFrame("Server");

		frame.setSize(800,800);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan = new ContentClient();
		this.setContentPane(pan);
		}
		
		
	}
	class ContentClient extends JPanel{
		private JLabel etiq;
		private JButton button;
		private JTextField text;
		public ContentClient() {
			super();
			proprieties();
			proprietiesbutton();
			proprietiestext();
		}
		private void proprieties() {
			etiq = new JLabel();
			this.etiq.setBounds(10,20,350,20);
			this.etiq.setText("Bienvenue sur Votre programme Client");
			this.add(etiq);
		}
		private void proprietiesbutton() {
			button = new JButton();
			this.etiq.setBounds(750,350,100,20);
			this.etiq.setText("Envoyer");
			this.add(button);
		}
		private void proprietiestext() {
			text = new JTextField();
			this.text.setBounds(100,20,350,20);
		}
	}
}
