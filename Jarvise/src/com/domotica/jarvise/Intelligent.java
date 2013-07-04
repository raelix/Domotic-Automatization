package com.domotica.jarvise;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

public class Intelligent {
	private static String nameFile = "jarvise.txt";
	private String host;
	private static int port = 20;
	ArrayList<String> text;
	public boolean domanda;
	public boolean risposta;
	public boolean fine;
	LinkedList<String> texts;
	int state;
	Configuration readFile;
	
	public Intelligent() {
		this.readFile = new Configuration(nameFile);
		this.host = readFile.getHost();
		this.state = 0;
		this.fine = false;
		this.risposta = false;
		this.domanda = true;
		this.text = new ArrayList<String>();
		this.texts = new LinkedList<String>();
	};

	public void execute(String frase){
		if(domanda){
			texts.addFirst(frase);
			
	if(texts.getFirst().matches(WORD_CONSTANTS.name)){
				String c[]=texts.getFirst().split("\\s",2);
				if(c[1].length() == 0){
					MainActivity.say("ciao, ben tornato");
					domanda = false;
					risposta = false;
					return;
				}
				else texts.addFirst(c[1]);
			}
			
	if(texts.getFirst().matches(WORD_CONSTANTS.cerca)){
				state = 1;
			}
			
	if(texts.getFirst().matches(WORD_CONSTANTS.cerca1)){
				state = 2;
			}
	
	if(texts.getFirst().matches(WORD_CONSTANTS.on)){
		state = 3;
	}
	
	if(texts.getFirst().matches(WORD_CONSTANTS.off)){
		state = 4;
	}
			
	if(texts.getFirst().matches(WORD_CONSTANTS.inizio) || (texts.getFirst().matches(WORD_CONSTANTS.inizio1))){
				if(texts.getFirst().matches(WORD_CONSTANTS.inizio1)){
					texts.addFirst(texts.getFirst().split("\\s",4)[3]);}
				else {
//					String c[]=texts.getFirst().split("\\s",3);
					texts.addFirst(texts.getFirst().split("\\s",3)[2]);}}
			
	if(texts.getFirst().matches(WORD_CONSTANTS.orario)){
				MainActivity.say("L'ora è,,,");
				while(MainActivity.talker.isSpeaking());
				MainActivity.say( java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime()));
				while(MainActivity.talker.isSpeaking());
				MainActivity.say("secondi");
				while(MainActivity.talker.isSpeaking());
				domanda = false;
				risposta = false;
				fine = true;
				return;
			}
			else {
				while(MainActivity.talker.isSpeaking());
			MainActivity.say("Hai detto "+texts.getFirst()+" ?");
			risposta = true;
			domanda = false;
			while(MainActivity.talker.isSpeaking());
			return;}
		}
		
		else if(risposta){
			texts.addLast(frase);
			String testo = texts.getLast();
			if(testo.matches(WORD_CONSTANTS.sbagliato)){
				MainActivity.say("Scusa ho capito male,,,ripeti???");
				while(MainActivity.talker.isSpeaking());
				risposta = false;
				domanda = true;
				return;}
			else if(testo.matches(WORD_CONSTANTS.giusto)){
				if(state==0)MainActivity.say(" OK spedisco");
				if(state==1 || state == 2)MainActivity.say(" OK cerco su internet");
				if(state==3)MainActivity.say(" OK provo ad Accendere");
				if(state==4)MainActivity.say(" OK provo a Spegnere");
				risposta = false;
				domanda = false;
				while(MainActivity.talker.isSpeaking());
				if(state==0)new MultiThread(host, port,new Pacco1(1));
				if(state==1)new MultiThread(host, port,new PaccoString((texts.getFirst().split("\\s",2)[1])));
				if(state==2)new MultiThread(host, port,new PaccoString((texts.getFirst().split("\\s",4)[3])));
				if(state==3)new MultiThread(host, port,new PaccoStringOn("Accensione: "+(texts.getFirst().split("\\s",2)[1])));
				if(state==4)new MultiThread(host, port,new PaccoStringOff("Spegnimento: "+(texts.getFirst().split("\\s",2)[1])));
				state = 0 ;
				while(MainActivity.talker.isSpeaking());
				fine = true;
				return;
			}
		}
		return;
	};









}


