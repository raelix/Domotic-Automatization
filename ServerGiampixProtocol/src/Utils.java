

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author raelix
 *
 */

public abstract class Utils {

	public static byte[] serializeFloat(float x){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream w = new DataOutputStream(baos);
		try {
			w.writeFloat(x);
			w.flush();
			w.close(); //FIXME
		} catch (IOException e) {
			return null;
		}
		return baos.toByteArray();
	}
	
	
	public static byte[] serializeString(String s){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream w = new DataOutputStream(baos);
		try {
			w.writeUTF(s);
			w.flush();
			w.close(); //FIXME
		} catch (IOException e) {
			return null;
		}
		return baos.toByteArray();
	}

	public static byte[] serialize2Float(float x, float y){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream w = new DataOutputStream(baos);
		try {
			w.writeFloat(x);
			w.writeFloat(y);
			w.flush();
			w.close(); //FIXME 
		} catch (IOException e) {
			return null;
		}
		return baos.toByteArray();
	}

	public static byte[] serializeInt(int x){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream w = new DataOutputStream(baos);
		try {
			w.writeInt(x);
			w.flush();
			w.close(); //FIXME 
		} catch (IOException e) {
			return null;
		}
		return baos.toByteArray();
	}
	
	public static byte[] serialize2Int(int pin,int bit){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream w = new DataOutputStream(baos);
		try {
			w.writeInt(pin);
			w.writeInt(bit);
			w.flush();
			w.close(); //FIXME 
		} catch (IOException e) {
			return null;
		}
		return baos.toByteArray();
	}
	
	public static void CreateNewFile(String destination) throws IOException{
		File newfile = new File(destination);
	    if (newfile.exists()) {
	        System.out.println("File esistente...Rimuoverlo!");
	    } else {
	        newfile.createNewFile();
	        System.out.println("Nuovo File creato");

	    }
	}
	
	public static class WriteToFile {
		private String path;
		private boolean append = true;

		public WriteToFile(String filename) {
		    path=filename;
		}

		public WriteToFile(String filename, boolean appendfile){
		    path=filename;
		    append=appendfile;
		}

		public void write(String text) throws IOException {
		    FileWriter filewrite = new FileWriter(path, append);
		    PrintWriter print = new PrintWriter(filewrite);

		    print.printf("%s" + "%n", text);
		    print.close();
		}
		}
}
