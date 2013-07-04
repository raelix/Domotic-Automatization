package com.domotica.jarviseSSH;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ProtocolException;


/**
 * @author raelix
 *
 */

public class PaccoString extends Pacco implements PROTOCOL_CONSTANTS {

	private String string;

	public PaccoString (String string) {
		super(PROTOCOL_CONSTANTS.PACKET_TYPE_STRING);
		byte[] payload = Utils.serializeString(string);
		this.setSize(payload.length);
		this.setData(payload);
		this.string=string;
	}

	public PaccoString (Pacco pkt) throws ProtocolException {
		super(pkt.getType(),pkt.getData(),pkt.getSize());
		if (this.getType() != PROTOCOL_CONSTANTS.PACKET_TYPE_STRING) {
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
