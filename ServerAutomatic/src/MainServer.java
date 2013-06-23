import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 
 */

/**
 * @author raelix
 *
 */
public class MainServer {

	private static ServerSocket serverSock;
	private static int PORT = 20;
	public static String destinationFile = "/Users/gianmarco/Desktop/LogServer.txt";
	public static WriteFile file;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Socket clientSock;
		Utils.CreateNewFile(destinationFile);
		file = new WriteFile(destinationFile);
		while(true){
			try{
				serverSock = new ServerSocket(PORT);
				System.out.println("Avvio il Server sulla porta = " + PORT);
				file.write("Avvio il Server sulla porta = " + PORT);
				clientSock = serverSock.accept();
				if (clientSock != null){
					System.out.println("Accetto Client in Entrata");
					file.write("Accetto Client in Entrata");
					}
				System.out.flush();
				ServerThread thread = new ServerThread(clientSock,serverSock);
				thread.start();
			}catch (IOException e) {
			}
		}

	};



};
