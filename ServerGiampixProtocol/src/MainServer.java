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
	/*esterno
	 * 5v   DNC  GND   15   16   1   DNC   4   5   DNC   6   10   11
	 *
	 * 3v    8    9     7   DNC  0    2    3  DNC  12    13  14   DNC
	 *interno
	 * DNC = Do Not Connect
	 *  da 0-3 liberi da 4-10 occupati
	 * */
	private static ServerSocket serverSock;
	public static Pin garageOn  	= RaspiPin.GPIO_04;		//Uso Specifiche Impostazioni
	public static Pin garageOff 	= RaspiPin.GPIO_05;		//Uso Specifiche Impostazioni
	public static Pin allarmeGarage	= RaspiPin.GPIO_06;		//Uso Specifiche Impostazioni
	public static Pin allarmeCasa   = RaspiPin.GPIO_07;		//Uso Specifiche Impostazioni
	public static Pin acquaAquario	= RaspiPin.GPIO_08;		//Uso Specifiche Impostazioni
	public static Pin acquaCasa 	= RaspiPin.GPIO_09;		//Uso Specifiche Impostazioni
	public static Pin move 			= RaspiPin.GPIO_10;		//Uso Specifiche Impostazioni
	public static Pin default0  	= RaspiPin.GPIO_00;		//Custom Relè GPIO_00------RELE' 1
	public static Pin default1  	= RaspiPin.GPIO_01;		//Custom Relè GPIO_01------RELE' 2
	public static Pin default2 	 	= RaspiPin.GPIO_02;		//Custom Relè GPIO_02------RELE' 3
	public static Pin default3 	    = RaspiPin.GPIO_03;		//Custom Relè GPIO_03------RELE' 4
	public static Pin default4  	= garageOn;		   		//Apertura Garage
	public static Pin default5  	= garageOff;	   		//Chiusura Garage
	public static Pin default6  	= allarmeGarage;   		//Allarme  Garage
	public static Pin default7  	= allarmeCasa;     		//Allarme  casa 
	public static Pin default8  	= acquaAquario;    		//Sensore acqua acquario
	public static Pin default9  	= acquaCasa;       		//sensore acqua casa
	public static Pin default10 	= move;            		//sensore movimento casa
	private static int PORT = 20;
	public static String destinationFile = "/home/raelix/LogServer.txt";
	public static boolean GPIO_00 	= false;
	public static boolean GPIO_01  	= false;
	public static boolean GPIO_02  	= false;
	public static boolean GPIO_03 	= false;
	public static boolean GPIO_04   = false;
	public static boolean GPIO_05  	= false;
	public static boolean GPIO_06  	= false;
	public static boolean GPIO_07  	= false;
	public static boolean GPIO_08  	= false; 
	public static boolean GPIO_09  	= false;
	public static boolean GPIO_10  	= false;
	public static  boolean garage = false;
	public static  boolean AllarmeGarage = false;
	public static  boolean AllarmeCasa = false;
	public static WriteFile file;
	public static GestioneGpio gpio = new GestioneGpio();
	public static String AcquaCasa = null;
	public static String AcquaAcquario = null;
	public static String movimento = null;
	public static  int sec = 5000;
	
	
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
	
public static int[] getStatus (){
	int status[]= new int[]{0,0,0,0,0,0,0,0,0,0};
	
	if(GPIO_00)status[0]=0;
	else if(!GPIO_00) status[0]=1;
	if(GPIO_01)status[1]=0;
	else if(!GPIO_01) status[1]=1;
	if(GPIO_02)status[2]=0;
	else if(!GPIO_02) status[2]=1;
	if(GPIO_03)status[3]=0;
	else if(!GPIO_03) status[3]=1;
	if(garage)status[4]=0;
	else if(!garage) status[4]=1;
	if(AllarmeGarage)status[5]=0;
	else if(!AllarmeGarage) status[5]=1;
	if(AllarmeCasa)status[6]=0;
	else if(!AllarmeCasa) status[6]=1;
	if(acquaAquario == null) status[7]=0;
	else if(acquaAquario != null) status[7]=1;
	if(acquaCasa == null) status[8]=0;
	else if(acquaCasa != null) status[8]=1;
	if(movimento == null) status[9]=0;
	else if(movimento != null) status[9]=1;
	
	return status;
	
}


};
