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
		public Send1Thread (String s) {
			super();
			this.s=s;
			System.out.println("Spedizione:Spedisco pacchetto di conferma");
			MainServer.file.write("Spedizione:Spedisco pacchetto di conferma");
		}

		@Override
		public void run () {
			PaccoString ResponsPacket=null;
			ResponsPacket = new PaccoString(s);
			System.out.println("Spedizione:spedito pacchetto al Client "+ResponsPacket.getType());
			MainServer.file.write("Spedizione:spedito pacchetto al Client "+ResponsPacket.getType());
			clientData.writePkt(ResponsPacket);
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
			Pacco pkt;
			pkt = clientData.readPkt();
			if (pkt != null)
				switch(pkt.getType()){
				
				case PROTOCOL_CONSTANTS.PACKET_TYPE_1:
					System.out.println("Ricezione: ricevuto pacco 1");
					MainServer.file.write("Ricezione: ricevuto pacco 1");
					try {
						Float f=new Pacco1(pkt).deserialize();
						System.out.println("Ricezione: Con valore= "+f);
						MainServer.file.write("Ricezione: Con valore= "+f);
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
						System.out.println("Ricezione: Con stringa= "+f);
						MainServer.file.write("Ricezione: Con stringa= "+f);
						f=f.replace( " ","%20" ); 
						java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://www.google.com/search?q="+f));
//						Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
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
						f=f.replace( " ","%20" ); 
						java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://www.google.com/search?q="+f));
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
						f=f.replace( " ","%20" ); 
						java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://www.google.com/search?q="+f));
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
					break;
				}

			if (pkt != null && pkt.getType() != PROTOCOL_CONSTANTS.PACKET_TYPE_START  ){
				System.out.println("Ricezione: finita");
				MainServer.file.write("Ricezione: finita");
				send1 = new Send1Thread(s);
				send1.start();
			}
			else {System.out.println("Ricezione: Pacchetto Nullo");
			MainServer.file.write("Ricezione: Pacchetto Nullo");
			}
		}
	}
}

