package com.domotica.jarvise;

import java.io.ByteArrayInputStream;
import java.io.IOException;
/**
 * 
 */
import java.io.DataInputStream;

/**
 * @author raelix
 *
 */
public class Pacco1 extends Pacco {
	private float bit ;


	public Pacco1(float i) {
		super(PROTOCOL_CONSTANTS.PACKET_TYPE_1,Utils.serializeFloat(i),Utils.serializeFloat(i).length);
		this.bit=i;
	}

	public Float deserialize() throws IOException {
		ByteArrayInputStream bas = new ByteArrayInputStream(this.getData());
		DataInputStream ds = new DataInputStream(bas);
		this.bit = ds.readFloat();
		return bit;
	}
}
