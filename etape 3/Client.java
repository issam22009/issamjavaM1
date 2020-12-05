
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Serializable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			Socket s = new Socket("localhost",123);
			System.out.println("Bienvenue sur votre application client : "); 

			InputStream is = s.getInputStream();
			InputStreamReader isr= new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			ObjectInputStream ois= new ObjectInputStream(is);
			
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			Object o = null;

			Scanner scanner = new Scanner(System.in);
			try {
				Class P=(Class) ois.readObject();
				System.out.println("un objet a été reçu "); 
				oos.writeObject(o);
				System.out.println("un objet a été envoyé "); 
				oos.close();
				os.close();
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

}