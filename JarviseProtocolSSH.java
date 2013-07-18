package com.domotica.jarviseSSH;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * @author raelix
 *
 */

public class JarviseProtocol {
	private static String nameFile = "jarvise.txt";
	private String default0;
	private String default1;
	private String default2;
	private String default3;
    private String default4;
	private String default5;
	private String default6;
	private String default7;
	private String default8;
	private String default9;
	private String default10;
	private String host;
	private static int port = 20;
	ArrayList<String> text;
	public boolean domanda;
	public boolean risposta;
	public boolean fine;
	LinkedList<String> texts;
	int state;
	Configuration readFile;
	
 public JarviseProtocol() {
	 this.readFile = new Configuration(nameFile);
	 this.default0 = readFile.getDefault0();
		this.default1 = readFile.getDefault1();
		this.default2 = readFile.getDefault2();
		this.default3 = readFile.getDefault3();
		this.default4 = readFile.getDefault4();
		this.default5 = readFile.getDefault5();
		this.default6 = readFile.getDefault6();
		this.default7 = readFile.getDefault7();
		this.default8 = readFile.getDefault8();
		this.default9 = readFile.getDefault9();
		this.default10 = readFile.getDefault10();
	 	this.host = readFile.getHost();
		this.state = -1;
		this.fine = false;
		this.risposta = false;
		this.domanda = true;
		this.text = new ArrayList<String>();
		this.texts = new LinkedList<String>();
	};

	/**
	 * @param frase
	 */
	public void execute(String frase){

		texts.addFirst(frase);
		
		if(texts.getFirst().matches(WORD_CONSTANTS.ciao)){
			if(texts.getFirst().contains(" ") ){
				if(texts.getFirst().matches(WORD_CONSTANTS.name)){
					String f[]=texts.getFirst().split("\\s",2);
					if(f[1].contains(" ") ){
						String k[]=texts.getFirst().split("\\s",2);
						execute(k[1]);
						return;
					}
					else MainActivity.say("ciao, ben tornato!");
					while(MainActivity.talker.isSpeaking());
					return;
				}
				else if(texts.getFirst().contains(" ") ){
					String k[]=texts.getFirst().split("\\s",2);
					execute(k[1]);
					return;
				}
			}
			else {MainActivity.say("ciao, ben tornato!");
			while(MainActivity.talker.isSpeaking());
			return;
			}
		}
		else if(texts.getFirst().matches(WORD_CONSTANTS.sbagliato)){
			MainActivity.say("ok mi dispiace, alla prossima");
			while(MainActivity.talker.isSpeaking());
			fine = true;
			return;
		}
		else if(texts.getFirst().matches(WORD_CONSTANTS.sentimento)){
			MainActivity.say("Benone, grazie,,vuoi che faccia qualcosa per te?");
			while(MainActivity.talker.isSpeaking());
			return;
			}
		
		else if(texts.getFirst().matches(WORD_CONSTANTS.ritornello)){
			MainActivity.say("la capra campa,sotto la panca,,la capra crepa,,la conosco anche io,bella filastrocca");
			while(MainActivity.talker.isSpeaking());
			fine = true;
			return;
			}
		
		
		

		else if(texts.getFirst().matches(WORD_CONSTANTS.on)){
				
					
			
				 if(texts.getFirst().matches(WORD_CONSTANTS.allarme)){
						if(texts.getFirst().matches(WORD_CONSTANTS.garage)){
							MainActivity.say(" OK provo ad Attivare l'allarme del garage");
							state = 6;}
						else if(texts.getFirst().matches(WORD_CONSTANTS.casa)){
						MainActivity.say(" OK provo ad Attivare l'allarme di casa");
					state = 7;}	
						else {MainActivity.say(" non ho capito di quale allarme parli");
						while(MainActivity.talker.isSpeaking());
						return;}
					}
				
				 else if(texts.getFirst().matches(WORD_CONSTANTS.garage)){
						MainActivity.say(" OK provo ad Aprire il garage");
						state = 4;
						}
					
				
				 else if(texts.getFirst().matches(".*"+default0+".*")){
						if(texts.getFirst().matches(".*[Aa]ccendi.*"))
							MainActivity.say(" OK provo a accendere");
					else if (texts.getFirst().matches("^[Aa]ttiv.*"))
						MainActivity.say(" OK provo a attivare");
					else if (texts.getFirst().matches(".*[Aa]pr.*"))
						MainActivity.say(" OK provo ad aprire");
					state = 0;
					}
				 
				 
					else if(texts.getFirst().matches(".*"+default1+".*")){
						if(texts.getFirst().matches(".*[Aa]ccendi.*"))
							MainActivity.say(" OK provo a accendere");
					else if (texts.getFirst().matches("^[Aa]ttiv.*"))
						MainActivity.say(" OK provo a attivare");
					else if (texts.getFirst().matches(".*[Aa]pr.*"))
						MainActivity.say(" OK provo ad aprire");
					state = 1;
					}
				
					else if(texts.getFirst().matches(".*"+default2+".*")){
						if(texts.getFirst().matches(".*[Aa]ccendi.*"))
							MainActivity.say(" OK provo a accendere");
					else if (texts.getFirst().matches("^[Aa]ttiv.*"))
						MainActivity.say(" OK provo a attivare");
					else if (texts.getFirst().matches(".*[Aa]pr.*"))
						MainActivity.say(" OK provo ad aprire");
						state = 2;
					}
				
					else if(texts.getFirst().matches(".*"+default3+".*")){
						if(texts.getFirst().matches(".*[Aa]ccendi.*"))
							MainActivity.say(" OK provo a accendere");
					else if (texts.getFirst().matches("^[Aa]ttiv.*"))
						MainActivity.say(" OK provo a attivare");
					else if (texts.getFirst().matches(".*[Aa]pr.*"))
						MainActivity.say(" OK provo ad aprire");
						state = 3;
					}
					
					else {
						while(MainActivity.talker.isSpeaking());
						MainActivity.say("Non ho capito ripeti?");
						fine= false;
						while(MainActivity.talker.isSpeaking());
						return;}
				
				while(MainActivity.talker.isSpeaking());

				onSuccess(1);
		}

		else if(texts.getFirst().matches(WORD_CONSTANTS.off)){
			
			
			if(texts.getFirst().matches(WORD_CONSTANTS.allarme)){
				if(texts.getFirst().matches(WORD_CONSTANTS.garage)){
						MainActivity.say(" OK provo a Disattivare l'allarme del garage");
						state = 6;}
					else if(texts.getFirst().matches(WORD_CONSTANTS.casa)){
					MainActivity.say(" OK provo a Disattivare l'allarme di casa");
				state = 7;}	
					else {MainActivity.say(" non ho capito di quale allarme parli");
					while(MainActivity.talker.isSpeaking());
					return;}
				}
			
			else if(texts.getFirst().matches(WORD_CONSTANTS.garage)){
				MainActivity.say(" OK provo a Chiudere il garage");
				state = 5;
				}
			
			
				else if(texts.getFirst().matches(".*"+default0+".*")){
				if(texts.getFirst().matches(".*[Ss]pegni.*"))
						MainActivity.say(" OK provo a spegnere");
				else if (texts.getFirst().matches(".*[Dd]isattiv.*"))
					MainActivity.say(" OK provo a disativare");
				else if (texts.getFirst().matches(".*[Cc]hiud.*"))
					MainActivity.say(" OK provo a chiudere");
				state = 0;
				}
				else if(texts.getFirst().matches(".*"+default1+".*")){
					if(texts.getFirst().matches(".*[Ss]pegni.*"))
						MainActivity.say(" OK provo a spegnere");
				else if (texts.getFirst().matches(".*[Dd]isattiv.*"))
					MainActivity.say(" OK provo a disativare");
				else if (texts.getFirst().matches(".*[Cc]hiud.*"))
					MainActivity.say(" OK provo a chiudere");
				state = 1;
				}
			
				else if(texts.getFirst().matches(".*"+default2+".*")){
					if(texts.getFirst().matches(".*[Ss]pegni.*"))
						MainActivity.say(" OK provo a spegnere");
				else if (texts.getFirst().matches(".*[Dd]isattiv.*"))
					MainActivity.say(" OK provo a disativare");
				else if (texts.getFirst().matches(".*[Cc]hiud.*"))
					MainActivity.say(" OK provo a chiudere");
				state = 2;
				}
			
				else if(texts.getFirst().matches(".*"+default3+".*")){
					if(texts.getFirst().matches(".*[Ss]pegni.*"))
						MainActivity.say(" OK provo a spegnere");
				else if (texts.getFirst().matches(".*[Dd]isattiv.*"))
					MainActivity.say(" OK provo a disativare");
				else if (texts.getFirst().matches(".*[Cc]hiud.*"))
					MainActivity.say(" OK provo a chiudere");
				state = 3;}
				
				else {
					while(MainActivity.talker.isSpeaking());
					MainActivity.say("Non ho capito ripeti?");
					fine= false;
					while(MainActivity.talker.isSpeaking());
					return;}
			
			onSuccess(0);
		}
		
		else if(texts.getFirst().matches(".*[Aa]cqu.*|"+".*[Pp]erd.*")){
			if(texts.getFirst().matches(".*[Aa]cquari.*")){
			state = 8;
			MainActivity.say(" Controllo perdite dall'acquario");
			while(MainActivity.talker.isSpeaking());}
			else if(texts.getFirst().matches(".*[Cc]asa.*")){
				state = 9;
				MainActivity.say(" Controllo perdite in casa");
				while(MainActivity.talker.isSpeaking());}
			else {MainActivity.say(" non ho capito di quale sensore d'acqua parli");
			while(MainActivity.talker.isSpeaking());
			return;}
			onSuccess(0);
		}
		
		
		else if(texts.getFirst().matches(WORD_CONSTANTS.movimento)){
			state = 10;
			MainActivity.say(" Controllo Ultimo movimento memorizzato");
			while(MainActivity.talker.isSpeaking());
			onSuccess(0);
		}


		else if(texts.getFirst().matches(WORD_CONSTANTS.orario)){
			MainActivity.say("L'ora è,,,");
			while(MainActivity.talker.isSpeaking());
			MainActivity.say( java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime()));
			while(MainActivity.talker.isSpeaking());
			MainActivity.say("secondi");
			while(MainActivity.talker.isSpeaking());
			fine = true;
		}
		/*
		else if(texts.getFirst().matches(WORD_CONSTANTS.entrata)){
			MainActivity.say("ok, me ne occupo io, Disattivo l'allarme e apro il garage");
			while(MainActivity.talker.isSpeaking());
			state = 5;
			onSuccess();
			}
		
		else if(texts.getFirst().matches(WORD_CONSTANTS.uscita)){
			MainActivity.say("ok, me ne occupo io, Attivo l'allarme e apro il garage");
			while(MainActivity.talker.isSpeaking());
			state = 6;
			onSuccess();
			return;
			}
		*/
		
		
		else {
			while(MainActivity.talker.isSpeaking());
			MainActivity.say("Non ho capito ripeti?");
			fine= false;
			while(MainActivity.talker.isSpeaking());
			return;}}

	public void onSuccess(int bit){
		fine = true;
		/*if(state==0)MainActivity.say(" OK spedisco");
		else if(state==1 || state == 2)MainActivity.say(" OK cerco su internet");
		else if(state==3)MainActivity.say(" OK provo ad Accendere");
		else if(state==4)MainActivity.say(" OK provo a Spegnere");
		while(MainActivity.talker.isSpeaking());
		*/
//		if(state <= 10 && state >= 0 && (bit==0 || bit==1)){new MultiThread(host, port,new PaccoGpio(state, bit));}
		switch (state){
		case 0:
			while(MainActivity.talker.isSpeaking());
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
			break;
		case 1:
			while(MainActivity.talker.isSpeaking());
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
			break;
		case 2:
			while(MainActivity.talker.isSpeaking());
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
			break;
		case 3:
			while(MainActivity.talker.isSpeaking());
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
			break;
		case 4:
			while(MainActivity.talker.isSpeaking());
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
			break;
		case 5:
			while(MainActivity.talker.isSpeaking());
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
			break;
		case 6:
			while(MainActivity.talker.isSpeaking());
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
		break;
		case 7:
			while(MainActivity.talker.isSpeaking());
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
		break;
		case 8:
			while(MainActivity.talker.isSpeaking());
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
		break;
		case 9:
			while(MainActivity.talker.isSpeaking());
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
		break;
		case 10:
			while(MainActivity.talker.isSpeaking());
		new MultiThread("127.0.0.1", 9001,new PaccoGpio(state, bit));
		break;
		}
		/*
		if(state==0)new MultiThread(host, port,new Pacco1(1));
		else if(state==1)new MultiThread(host, port,new PaccoString((texts.getFirst().split("\\s",2)[1])));
		else if(state==2)new MultiThread(host, port,new PaccoString((texts.getFirst().split("\\s",4)[3])));
		else if(state==3)new MultiThread(host, port,new PaccoStringOn("Accensione: "+(texts.getFirst().split("\\s",2)[1])));
		else if(state==4)new MultiThread(host, port,new PaccoStringOff("Spegnimento: "+(texts.getFirst().split("\\s",2)[1])));
		*/
		state = -1 ;
		while(MainActivity.talker.isSpeaking());
		
		return;
	}

};



