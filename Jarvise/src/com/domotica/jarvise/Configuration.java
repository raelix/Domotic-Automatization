/**
 * 
 */
package com.domotica.jarvise;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.os.Environment;

/** <p>
 * @author raelix
 * <p>
 * @Uses
 * {@link Configuration} Class that help you to read a file..
 * </p>
 * <p>
 * Configuration has nameFile coordinate.You must call this Class with  <strong>String nameFile</strong> , it will return host,user and password.
 * </p> */
public class Configuration {
	private File sdcard; 
	private File file;
	private String host;
	private String user;
	private String password;
	BufferedReader br;

	public Configuration(String source) {
		sdcard = Environment.getExternalStorageDirectory();
		file = new File(sdcard,source);
		try {
			br = new BufferedReader(new FileReader(file));
			this.host = br.readLine();
			this.user = br.readLine();
			this.password = br.readLine();
		}
		catch (IOException e) {
			System.out.println("errore caricamento file "+e.getMessage());
		}
	}

	public String getHost(){
		return(this.host);
	}
	public String getUser(){
		return(this.user);
	}
	public String getPass(){
		return(this.password);
	}


}
