/**
 * 
 */
package com.domomtica.JarviseRemote;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	
	public void refresh(){
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
	
	public void write2File(String name, String passwd, String hoster, String def1, String def2,String def3,String def4) throws IOException{
String h=this.getHost();
String n=this.getUser();
String p=this.getPass();
String d1=this.getDefault0();
String d2=this.getDefault1();
String d3=this.getDefault2();
String d4=this.getDefault3();
@SuppressWarnings("resource")
FileWriter writer = new FileWriter(Environment.getExternalStorageDirectory()+"/jarvise.txt");

if(hoster.length() != 0){
 writer.write(hoster);
 writer.write("\n");
}
else if(h != null && h.length() != 0){
	writer.write(h);
	writer.write("\n");}
else {
	writer.write("host");
	writer.write("\n");
}
if(name.length() != 0){
	writer.write(name);
	writer.write("\n");	
}
else if(n != null && n.length() != 0){
	writer.write(n);
	writer.write("\n");
}
else {	writer.write("user");
		writer.write("\n");
}
if(passwd.length() != 0){
	writer.write(passwd);
	writer.write("\n");	
}
else if(p != null && p.length() != 0){
	writer.write(p);
	writer.write("\n");}
else{ writer.write("password");
writer.write("\n");}
if(def1.length() != 0){
	writer.write(def1);
	writer.write("\n");	
}
else if(d1 != null && d1.length()!= 0){
	writer.write(d1);
	writer.write("\n");}
else {writer.write("default1");
writer.write("\n");
}
if(def2.length() != 0){
	writer.write(def2);
	writer.write("\n");	
}
else if(d2 != null && d2.length()!= 0){
	writer.write(d2);
	writer.write("\n");}
else {
	writer.write("default1");
	writer.write("\n");
}

if(def3.length() != 0){
	writer.write(def3);
	writer.write("\n");	
}
else if(d3 != null && d3.length()!= 0){
	writer.write(d3);
	writer.write("\n");}
else {
	writer.write("default3");
	writer.write("\n");
}

if(def4.length() != 0){
	writer.write(def4);
	writer.write("\n");	
}
else if(d4 != null && d4.length()!= 0){
	writer.write(d4);
	writer.write("\n");}
else {
	writer.write("default4");
	writer.write("\n");
}
writer.flush();
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
