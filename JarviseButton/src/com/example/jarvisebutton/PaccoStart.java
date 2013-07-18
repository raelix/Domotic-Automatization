package com.example.jarvisebutton;

import java.net.ProtocolException;



/**
 * @author	raelix
 *
 */


public class PaccoStart extends Pacco implements PROTOCOL_CONSTANTS {
	

	public PaccoStart() {
		super(PROTOCOL_CONSTANTS.PACKET_TYPE_START);
	}

	public PaccoStart (Pacco pkt) throws ProtocolException  {
		super(pkt.getType(),pkt.getData(),pkt.getSize());
		if (this.getType() != PROTOCOL_CONSTANTS.PACKET_TYPE_START) {
			throw new ProtocolException("TYPE SBAGLIATO.");
		}
	}



}
