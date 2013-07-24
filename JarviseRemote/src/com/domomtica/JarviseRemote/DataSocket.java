package com.domomtica.JarviseRemote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author raelix
 *
 */

public class DataSocket {
	private InputStream in;
	private OutputStream out;
	private DataOutputStream dataout;
	private DataInputStream datain;

	public DataSocket(InputStream in, OutputStream out){
		this.in = in;
		this.out = out;
		this.datain = new DataInputStream(in);
		this.dataout = new DataOutputStream(out);
	}

	public Pacco readPkt(){
		int len = 0, type = 0, i = 0;
		byte[] payload;
		try {
			len = datain.readInt();
			type = datain.readInt();
			payload = new byte[len];
			while (i < len)
				i += datain.read(payload,i,len-i);
		} catch (IOException e) {
			return null;
		}
		return new Pacco(type,payload,len);
	}

	public int writePkt(Pacco pkt){
		try {
			byte[] bin = pkt.getSerialized();
			dataout.write(bin,0,bin.length);
		} catch (IOException e) {
			return -1;
		}
		return pkt.getSize();
	}

	@SuppressWarnings("unused")
	private void writeInt(int i) {
		try {
			dataout.writeInt(i);
		} catch (IOException e) {
		}
	}

	@SuppressWarnings("unused")
	private void writeFloat(float f) {
		try {
			dataout.writeFloat(f);
		} catch (IOException e) {
		}
	}

	public void close() {
		try {
			this.datain.close();
			this.dataout.close();
			this.in.close();
			this.out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
