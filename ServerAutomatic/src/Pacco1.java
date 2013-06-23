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
public class Pacco1 extends Pacco {
	private float bit ;


	public Pacco1(float i) {
		super(PROTOCOL_CONSTANTS.PACKET_TYPE_1,Utils.serializeFloat(i),Utils.serializeFloat(i).length);
		this.bit=i;
	}

	public Pacco1(Pacco pkt){
	super(pkt.getType(),pkt.getData(),pkt.getSize());
	}
	
	public Float deserialize() throws IOException {
		ByteArrayInputStream bas = new ByteArrayInputStream(this.getData());
		DataInputStream ds = new DataInputStream(bas);
		this.bit = ds.readFloat();
		return bit;
	}
}
