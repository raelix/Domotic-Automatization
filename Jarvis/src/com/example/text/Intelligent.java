package com.example.text;

import java.util.ArrayList;





public class Intelligent {
	ArrayList<String> text;
	boolean richiesta;

	
	public Intelligent() {
		richiesta = true;
		text = new ArrayList<String>();
	};
	
	public String execute(String frase){
		if(richiesta)text.add(frase);
		richiesta = false;
		
		
		
		
		return frase;
		
		
	};

}



