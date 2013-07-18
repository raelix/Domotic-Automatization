/**
 * 
 */
package com.example.jarvisebutton;
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
	BufferedReader br;

	public Configuration(String source) {
		sdcard = Environment.getExternalStorageDirectory();
		file = new File(sdcard,source);
		try {
			br = new BufferedReader(new FileReader(file));
			this.host = br.readLine();
			this.user = br.readLine();
			this.password = br.readLine();
			this.default0 = br.readLine();
			this.default1 = br.readLine();
			this.default2 = br.readLine();
			this.default3 = br.readLine();
			this.default4 = br.readLine();
			this.default5 = br.readLine();
			this.default6 = br.readLine();
			this.default7 = br.readLine();
			this.default8 = br.readLine();
			this.default9 = br.readLine();
			this.default10 = br.readLine();
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
	
	public String getDefault0(){
		return(this.default0);
	}
	
	public String getDefault1(){
		return(this.default1);
	}

	public String getDefault2(){
		return(this.default2);
	}
	
	public String getDefault3(){
		return(this.default3);
	}
	public String getDefault4(){
		return(this.default4);
	}
	public String getDefault5(){
		return(this.default5);
	}
	public String getDefault6(){
		return(this.default6);
	}
	public String getDefault7(){
		return(this.default7);
	}
	public String getDefault8(){
		return(this.default8);
	}
	public String getDefault9(){
		return(this.default9);
	}
	public String getDefault10(){
		return(this.default10);
	}
}
