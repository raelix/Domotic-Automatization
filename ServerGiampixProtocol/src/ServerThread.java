import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author raelix
 *
 */
public class ServerThread extends Thread implements PROTOCOL_CONSTANTS{
	
	Utils.WriteToFile newFile = new Utils.WriteToFile(MainServer.destinationFile, true);
	ServerSocket socketS;
	Socket clientSock;
	DataSocket clientData;
	Send1Thread send1;
	Recv1Thread recv1;

	public ServerThread(Socket clientSock,ServerSocket socketS ) {
		super();
		System.out.println("Avvio Thread di Connessione");
		MainServer.file.write("Avvio Thread di Connessione");
		this.clientSock = clientSock;
		this.socketS= socketS;
		try {
			this.clientData = new DataSocket(clientSock.getInputStream(),clientSock.getOutputStream());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void start() {
		System.out.println("Ricevo pacchetto di Riconoscimento");
		MainServer.file.write("Ricevo pacchetto di Riconoscimento");
		Pacco pkt1 = clientData.readPkt();
		if(pkt1 != null){
			if (pkt1.getType() != PROTOCOL_CONSTANTS.PACKET_TYPE_START){
				System.out.println("Ricezione: Errore Protocollo o Riconoscimento Errato");
				MainServer.file.write("Ricezione: Errore Protocollo o Riconoscimento Errato");
				return;
			}
		if (pkt1.getType() == PROTOCOL_CONSTANTS.PACKET_TYPE_START){
			System.out.println("Ricezione: Riconoscimento avvenuto con Successo");
			MainServer.file.write("Ricezione: Riconoscimento avvenuto con Successo");
		}
		
		recv1 = new Recv1Thread();
		recv1.start();}
		else if(pkt1 == null){
			System.out.println("Pacchetto nullo Disconnetto");
			clientData.close();
			try {
				clientSock.close();
				socketS.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}



	private class Send1Thread extends Thread {
		private String s;
		private int [] status;
		private boolean string = false;
		public Send1Thread (String s) {
			super();
			this.s=s;
			this.string = true;
			System.out.println("Spedizione:Spedisco pacchetto di conferma");
			MainServer.file.write("Spedizione:Spedisco pacchetto di conferma");
		}
		
		public Send1Thread (int [] status) {
			super();
			this.status = status;
			this.string = false;
			System.out.println("Spedizione:Spedisco pacchetto di conferma");
			MainServer.file.write("Spedizione:Spedisco pacchetto di conferma");
		}

		@Override
		public void run () {
			PaccoStatus ResponsPacketStatus=null;
			PaccoString ResponsPacketString=null;
			if(string) {
				ResponsPacketString = new PaccoString(s);
				clientData.writePkt(ResponsPacketString);
			}
			else{
				ResponsPacketStatus = new PaccoStatus(status);
				clientData.writePkt(ResponsPacketStatus);
				
			}
//			System.out.println("Spedizione:spedito pacchetto al Client "+ResponsPacket.getType());
//			MainServer.file.write("Spedizione:spedito pacchetto al Client "+ResponsPacket.getType());
			System.out.println("Spedizione: finita");
			MainServer.file.write("Spedizione: finita");
			this.close();
		}


		public void close(){
			System.out.println("Server: Chiudo");
			MainServer.file.write("Server: Chiudo");
			System.out.println("\n");
			MainServer.file.write("\n");
			clientData.close();
			
			try {
				clientSock.close();
				socketS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class Recv1Thread extends Thread {

		public Recv1Thread () {
			super();
		}

		@Override
		public void run () {
			System.out.println("Ricezione: Ricevo Comando dal Client");
			MainServer.file.write("Ricezione: Ricevo Comando dal Client");
			String s="ricevuto correttamente";
			int [] stato = null;
			Pacco pkt;
			pkt = clientData.readPkt();
			if (pkt != null)
				
				switch (pkt.getType()) {
				
				case PROTOCOL_CONSTANTS.PACKET_TYPE_STATUS:
					stato = MainServer.getStatus();
					break;
					
					
				case PROTOCOL_CONSTANTS.PACKET_TYPE_GPIO:
					int [] gpio = new PaccoGpio(pkt).deserialize();
					switch (gpio[0]){
					
					case PROTOCOL_CONSTANTS.GPIO_00:
						if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_ON){
							s = MainServer.gpio.accendi(0);
						}
						else if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_OFF){
							s = MainServer.gpio.spegni(0);
						}
						break;
					
					case PROTOCOL_CONSTANTS.GPIO_01:
						if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_ON){
							s = MainServer.gpio.accendi(1);
						}
						else if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_OFF){
							s = MainServer.gpio.spegni(1);
						}
						break;
					
					case PROTOCOL_CONSTANTS.GPIO_02:
						if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_ON){
							s = MainServer.gpio.accendi(2);
						}
						else if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_OFF){
							s = MainServer.gpio.spegni(2);
						}
						break;
					
					case PROTOCOL_CONSTANTS.GPIO_03:
						if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_ON){
							s = MainServer.gpio.accendi(3);
						}
						else if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_OFF){
							s = MainServer.gpio.spegni(3);
						}
						break;
					
					case PROTOCOL_CONSTANTS.GPIO_04:
						if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_ON){
							s = MainServer.gpio.apriGarage();
							MainServer.file.write("Ricezione: Apro garage "+s);
						}
						/*else if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_OFF){
							s = MainServer.gpio.chiudiGarage();							LAVORA SU DUE RELE' DISTINTI 1 APRE L'ALTRO CHIUDE
							MainServer.file.write("Ricezione: Chiudo garage "+s);
						}*/
						break;
					
					case PROTOCOL_CONSTANTS.GPIO_05:
					  /*	if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_ON){
							s = MainServer.gpio.chiudiGarage();						     LAVORA SU DUE RELE' DISTINTI 1 APRE L'ALTRO CHIUDE
							MainServer.file.write("Ricezione: Apro Garage "+s);
						}*/
						if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_OFF){
							s = MainServer.gpio.chiudiGarage();
							MainServer.file.write("Ricezione: Chiudo Garage "+s);
						}
						break;
					
					case PROTOCOL_CONSTANTS.GPIO_06:
						if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_ON){
							s = MainServer.gpio.attivaAllarmeGarage();
							MainServer.file.write("Ricezione: Attivo Allarme Garage "+s);
						}
						else if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_OFF){
							s = MainServer.gpio.disattivaAllarmeGarage();
							MainServer.file.write("Ricezione: Disattivo Allarme Garage "+s);
						}
						break;
					
					case PROTOCOL_CONSTANTS.GPIO_07:
						if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_ON){
							s = MainServer.gpio.attivaAllarmeCasa();
							MainServer.file.write("Ricezione: Attivo Allarme Casa "+s);
						}
						else if(gpio[1] == PROTOCOL_CONSTANTS.GPIO_OFF){
							s = MainServer.gpio.disattivaAllarmeCasa();
							MainServer.file.write("Ricezione: Disattivo Allarme Casa "+s);
						}
						break;
					
					case PROTOCOL_CONSTANTS.GPIO_08:
						if (MainServer.AcquaAcquario != null)
						s = "Si ho trovato dell'acqua il "+MainServer.AcquaAcquario;
						else s = "Nessuna traccia di acqua fin ora";
						MainServer.file.write("Ricezione: "+s);
						break;
					
					case PROTOCOL_CONSTANTS.GPIO_09:
						if (MainServer.AcquaCasa != null)
							s = "Si ho trovato dell'acqua il "+MainServer.AcquaCasa;
							else s = "Nessuna traccia di acqua fin ora";
							MainServer.file.write("Ricezione: "+s);
						break;
					
					case PROTOCOL_CONSTANTS.GPIO_10:
						if (MainServer.movimento != null)
						s = "Ultimo movimento  il "+MainServer.movimento;
						else s = "Nessun movimento fin ora";
						MainServer.file.write("Ricezione: "+s);
						break;
					
					}
				
				/*
				case PROTOCOL_CONSTANTS.PACKET_TYPE_GPIO:
					System.out.println("Ricezione: ricevuto pacco gestione Gpio");
					MainServer.file.write("Ricezione: ricevuto pacco gestione Gpio");
					try {
						float[] f=new PaccoGpio1(pkt).deserialize();
						System.out.println("Ricezione: Con valore= "+f[1]);
						MainServer.file.write("Ricezione: Con valore= "+f[1]);
						System.out.println("Ricezione: Con valore= "+f[1]);
						MainServer.file.write("Ricezione: Con valore= "+f[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case PROTOCOL_CONSTANTS.PACKET_TYPE_2:
					System.out.println("Ricezione: ricevuto pacco 2");
					MainServer.file.write("Ricezione: ricevuto pacco 2");
					break;

				case PROTOCOL_CONSTANTS.PACKET_TYPE_3:
					System.out.println("Ricezione: ricevuto pacco 3");
					MainServer.file.write("Ricezione: ricevuto pacco 3");
					break;

				case PROTOCOL_CONSTANTS.PACKET_TYPE_4:
					System.out.println("Ricezione: ricevuto pacco 4");
					MainServer.file.write("Ricezione: ricevuto pacco 4");
					break;	

				case PROTOCOL_CONSTANTS.PACKET_TYPE_STRING:
					System.out.println("Ricezione: ricevuto pacco 5");
					MainServer.file.write("Ricezione: ricevuto pacco 5");
					try {
						String f=new PaccoString(pkt).getString();
						if(f.matches(WORD_CONSTANTS.movimento)){
							//FIXME es. s=gpio.accenditelecamera();
							s = "Ultimo movimento  alle ore "+MainServer.movimento+" secondi";
							MainServer.file.write("Ricezione: "+s);
						}
						//						Runtime.getRuntime().exec("taskkill /F /FI "USERNAME eq Quinn"); killa tt
//						Process p  = Runtime.getRuntime().exec("notepad.exe");
//						p.waitFor(); attende che il processo sia terminato (meglio fare tt come thread)
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case PROTOCOL_CONSTANTS.PACKET_TYPE_STRINGON:
					System.out.println("Ricezione: ricevuto pacco Accensione");
					MainServer.file.write("Ricezione: ricevuto pacco Accensione");
					try {
						String f=new PaccoStringOn(pkt).getString();
						System.out.println("Ricezione: Con stringa= "+f);
						MainServer.file.write("Ricezione: Con stringa= "+f);
						if(f.matches(WORD_CONSTANTS.luce)){
							//FIXME es. s=gpio.accendiluce();
						s = MainServer.gpio.accendiluce();
						MainServer.file.write("Ricezione: Accendo luce "+s);
						}
						else if(f.matches(WORD_CONSTANTS.telecamera)){
							//FIXME es. s=gpio.accenditelecamera();
							s = MainServer.gpio.accenditelecamera();
							MainServer.file.write("Ricezione: Accendo telecamera "+s);
						}
						else if(f.matches(WORD_CONSTANTS.allarme)){
							//FIXME es. s=gpio.accenditelecamera();
							s = MainServer.gpio.attivaAllarme();
							MainServer.file.write("Ricezione: Attivo allarme "+s);
						}
						else if(f.matches(WORD_CONSTANTS.garage)){
							//FIXME es. s=gpio.accenditelecamera();
							s = MainServer.gpio.apriGarage();
							MainServer.file.write("Ricezione: Apro garage "+s);
						}
						else if(f.matches(WORD_CONSTANTS.movimento)){
							//FIXME es. s=gpio.accenditelecamera();
							s = "Ultimo movimento  il "+MainServer.movimento;
							MainServer.file.write("Ricezione: "+s);
						}
						else s = "non ho capito cosa devo accendere";
//						f=f.replace( " ","%20" ); 
//						java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://www.google.com/search?q="+f));
//						Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
//						Runtime.getRuntime().exec("taskkill /F /FI "USERNAME eq Quinn"); killa tt
//						Process p  = Runtime.getRuntime().exec("notepad.exe");
//						p.waitFor(); attende che il processo sia terminato (meglio fare tt come thread)
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
					
				case PROTOCOL_CONSTANTS.PACKET_TYPE_STRINGOFF:
					System.out.println("Ricezione: ricevuto pacco Spegnimento");
					MainServer.file.write("Ricezione: ricevuto pacco Spegnimento");
					try {
						String f=new PaccoStringOff(pkt).getString();
						System.out.println("Ricezione: Con stringa= "+f);
						MainServer.file.write("Ricezione: Con stringa= "+f);
						if(f.matches(WORD_CONSTANTS.luce)){
							//FIXME es. s=gpio.spegniluce();
							s = MainServer.gpio.spegniluce();
							System.out.println("RRicezione: Spengo Luce "+s);
							MainServer.file.write("Ricezione: Spengo Luce "+s);
						}
						else if(f.matches(WORD_CONSTANTS.telecamera)){
							//FIXME es. s=gpio.spegnitelecamera();
							s = MainServer.gpio.spegnitelecamera();
							MainServer.file.write("Ricezione: Spengo Telecamera "+s);
						}
						else if(f.matches(WORD_CONSTANTS.allarme)){
							//FIXME es. s=gpio.accenditelecamera();
							s = MainServer.gpio.disattivaAllarme();
							MainServer.file.write("Ricezione: Disattivo allarme "+s);
						}
						else if(f.matches(WORD_CONSTANTS.garage)){
							//FIXME es. s=gpio.accenditelecamera();
							s = MainServer.gpio.chiudiGarage();
							MainServer.file.write("Ricezione: Chiudo garage "+s);
						}
						else s = "non ho capito cosa devo spegnere";
//						f=f.replace( " ","%20" ); 
//						java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://www.google.com/search?q="+f));
//						Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
//						Runtime.getRuntime().exec("taskkill /F /FI "USERNAME eq Quinn"); killa tt
//						Process p  = Runtime.getRuntime().exec("notepad.exe");
//						p.waitFor(); attende che il processo sia terminato (meglio fare tt come thread)
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
					
				default:
					System.out.println("Ricezione: ricevuto pacco " + pkt.getType());
					MainServer.file.write("Ricezione: ricevuto pacco " + pkt.getType());
					break;*/
				}

			if (pkt != null && pkt.getType() == PROTOCOL_CONSTANTS.PACKET_TYPE_GPIO  ){
				System.out.println("Ricezione: finita");
				MainServer.file.write("Ricezione: finita");
				send1 = new Send1Thread(s);
				send1.start();
			}
			else if (pkt != null && pkt.getType() == PROTOCOL_CONSTANTS.PACKET_TYPE_STATUS  ){
				System.out.println("Ricezione: finita");
				MainServer.file.write("Ricezione: finita");
				send1 = new Send1Thread(stato);
				send1.start();
			}
			else {System.out.println("Ricezione: Pacchetto Nullo");
			MainServer.file.write("Ricezione: Pacchetto Nullo");
			}
		}
	}
}

