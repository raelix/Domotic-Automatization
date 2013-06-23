package com.example.text;
import java.io.IOException;
import java.net.UnknownHostException;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		new ConnectThread("192.168.0.2", 10000, new Pacco1(3));

	}

}
