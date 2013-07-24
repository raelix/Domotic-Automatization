package com.domomtica.JarviseRemote;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


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
	
	public static byte[] serialize2Int(int x,int y){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream w = new DataOutputStream(baos);
		try {
			w.writeInt(x);
			w.writeInt(y);
			w.flush();
			w.close(); //FIXME 
		} catch (IOException e) {
			return null;
		}
		return baos.toByteArray();
	}
	
	public static byte[] serializeManyInt(int ...x){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream w = new DataOutputStream(baos);
		try {
			int i = 0;
			int legth = x.length;
			while(i != legth){
				w.writeInt(x[i]);
				i++;
			}
			w.flush();
			w.close(); //FIXME 
		} catch (IOException e) {
			return null;
		}
		return baos.toByteArray();
	}
}
