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

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket(1234);
			System.out.println("Bienvenue sur votre application !");
			Socket s = ss.accept(); 
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			System.out.println("j'attend un entier ");

			int nb = is.read();
			int rep=nb+1;
			os.write(rep);
			System.out.println("réponse envoyée par succés ");
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}

}
