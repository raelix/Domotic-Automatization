package com.domomtica.JarviseRemote;
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
public class PaccoStatus extends Pacco {

	int setting[] = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};

	public PaccoStatus(int...x) {
		super(PROTOCOL_CONSTANTS.PACKET_TYPE_STATUS,Utils.serializeManyInt(x),Utils.serializeManyInt(x).length);

	}

	public PaccoStatus(Pacco pkt){
		super(pkt.getType(),pkt.getData(),pkt.getSize());
	}

	public int[] deserialize() {
		ByteArrayInputStream bas = new ByteArrayInputStream(this.getData());
		DataInputStream ds = new DataInputStream(bas);
		try {
			int i = 0 ;
			while(ds.available() > 0 )
			{
				
				setting[i] =  ds.readInt() ;
				i++;
			}
		} catch (IOException e) {
			System.out.println("can not deserialize");
			e.printStackTrace();
		}

		return  setting;

	}


	public int[] getSettings(){
		return setting;
	}
}
