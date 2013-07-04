package com.domotica.jarvise;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ProtocolException;


/**
 * @author raelix
 *
 */

public class PaccoStringOff extends Pacco implements PROTOCOL_CONSTANTS {

	private String string;

	public PaccoStringOff (String string) {
		super(PROTOCOL_CONSTANTS.PACKET_TYPE_STRINGOFF);
		byte[] payload = Utils.serializeString(string);
		this.setSize(payload.length);
		this.setData(payload);
		this.string=string;
	}

	public PaccoStringOff (Pacco pkt) throws ProtocolException {
		super(pkt.getType(),pkt.getData(),pkt.getSize());
		if (this.getType() != PROTOCOL_CONSTANTS.PACKET_TYPE_STRINGOFF) {
			throw new ProtocolException("TYPE PACCHETTO SBAGLIATO.");
		}
		ByteArrayInputStream bas = new ByteArrayInputStream(pkt.getData());
		DataInputStream ds = new DataInputStream(bas);
		try {
			this.string = ds.readUTF();
		} catch (IOException e) {
			throw new ProtocolException("ERRORE NEL PROTOCOLLO.");
		}
	}

	public String getString() {
		return string;
	}

	

}
