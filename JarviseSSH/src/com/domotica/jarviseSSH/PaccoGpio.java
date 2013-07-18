package com.domotica.jarviseSSH;
/**
 * 
 */


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author raelix
 *
 */
public class PaccoGpio extends Pacco {
	private int pin, bit ;


	public PaccoGpio(int gpio, int position) {
		super(PROTOCOL_CONSTANTS.PACKET_TYPE_GPIO,Utils.serialize2Int(gpio, position),Utils.serialize2Int(gpio, position).length);
		this.pin=gpio;
		this.bit=position;
	}

	public PaccoGpio(Pacco pkt){
	super(pkt.getType(),pkt.getData(),pkt.getSize());
	}
	
	public int[] deserialize() {
		ByteArrayInputStream bas = new ByteArrayInputStream(this.getData());
		DataInputStream ds = new DataInputStream(bas);
		try {
			this.pin = ds.readInt();
			this.bit = ds.readInt();
		} catch (IOException e) {
			System.out.println("can not deserialize");
			e.printStackTrace();
		}
		
		return  new int[] {pin,bit};
		
	}
	
	public int pin(){return this.pin;};
	public int bit(){return this.bit;};
}
