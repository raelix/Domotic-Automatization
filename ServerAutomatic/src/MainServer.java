import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.pi4j.io.gpio.Pin;
/**
 * 
 */
import com.pi4j.io.gpio.RaspiPin;

/**
 * @author raelix
 *
 */
public class MainServer {
	private static ServerSocket serverSock;
	public static Pin rele = RaspiPin.GPIO_00;//pos6
	public static Pin tele = RaspiPin.GPIO_02;//pos7
	public static Pin move = RaspiPin.GPIO_07;//pos4
	public static Pin garageOn; //= RaspiPin.GPIO_01;//pos da scegliere
	public static Pin garageOff;// = RaspiPin.GPIO_01;//pos da scegliere
	public static Pin allarme;// = RaspiPin.GPIO_01;//pos da scegliere 
	private static int PORT = 20;
	public static String destinationFile = "/home/raelix/LogServer.txt";
	public static  boolean luce = false;
	public static boolean telecamera = false;
	public static  boolean garage = false;
	public static  boolean allarm = false;
	public static WriteFile file;
	public static GestioneGpio gpio = new GestioneGpio();
	public static String movimento = null;
	public static int sec = 5000;
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
