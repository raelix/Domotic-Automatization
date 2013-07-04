package com.domotica.jarvise;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.Socket;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;




/**
 * @author raelix
 *
 */
@SuppressLint("NewApi")
public class MultiThread{
	Socket sock;
	DataSocket btsock;
	int port;
	String dest;
	Pacco pkt;
	String returned;
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@SuppressWarnings("unchecked")
	public MultiThread(String dest, int port,Pacco pkt){
		this.dest = dest;
		this.port = port;
		this.pkt = pkt;
		new Connection().execute();
	};


	@SuppressWarnings("rawtypes")
	private class Connection extends AsyncTask {

		@Override
		protected Object doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			connect();
			return null;
		}
		@SuppressWarnings("unchecked")
		private void connect(){
			try {
				sock = new Socket(dest,port);
				System.out.println("Connect Thread:  Inizio");
				btsock = new DataSocket(sock.getInputStream(),sock.getOutputStream());

			} catch (IOException e) {
				System.out.println("Connect Thread: Errore");
				e.printStackTrace();
			}
			System.out.println("Connect Thread: Scrivo pacco di riconoscimento");
			btsock.writePkt(new PaccoStart());
			System.out.println("Connect Thread: Adesso spedisco le informazioni");
			btsock.writePkt(pkt);
			new ConnectionServer().execute();
		};
	};



	@SuppressWarnings({ "rawtypes" })
	private class ConnectionServer extends AsyncTask {

		@Override
		protected Object doInBackground(Object... arg0) {

			connected();
			return null;
		}
		public void connected(){
			Pacco p = btsock.readPkt();
			if (p == null || p.getType() != PROTOCOL_CONSTANTS.PACKET_TYPE_STRING){
				this.close();
				return;
			}
			else if(p.getType() == PROTOCOL_CONSTANTS.PACKET_TYPE_STRING){
				try {
					System.out.println("Ricevuta Stringa di ritorno dal Server: "+new PaccoString(p).getString());
					MainActivity.say(new PaccoString(p).getString());
					this.close();
				} catch (ProtocolException e) {
					e.printStackTrace();
				}
			}
		};

		private void close(){
			System.out.println("Ricezione: Finito");
			btsock.close();
			try {
				sock.close();
				System.out.println("Chiuso");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};



}

