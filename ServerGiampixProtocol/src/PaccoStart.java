import java.net.ProtocolException;

/**
 * @author phra
 *
 */


public class PaccoStart extends Pacco implements PROTOCOL_CONSTANTS {

	PaccoStart (Pacco pkt) throws ProtocolException {
		super(pkt.getType(),pkt.getData(),pkt.getSize());
		if (this.getType() != PROTOCOL_CONSTANTS.PACKET_TYPE_START) {
			throw new ProtocolException("PACCHETTO ERRATO.");
		}
		
	}


}
