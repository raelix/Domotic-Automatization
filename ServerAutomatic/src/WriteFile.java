import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * 
 */


/**
 * @author gianmarco
 *
 */
public class WriteFile {
	Utils.WriteToFile newFile;
	/**
	 * @throws IOException 
	 * 
	 */
	/**
	 * @param destinationFile
	 * @throws IOException
	 */
	public WriteFile(String destinationFile) throws IOException {
		this.newFile = new Utils.WriteToFile(destinationFile, true);
		newFile.write("Orario            Azione   "); 
		newFile.write(""); 
		newFile.write("");
	}
	
	/**
	 * @param scrivo
	 */
	public void write(String scrivo){
		try {
			newFile.write(DateFormat.getTimeInstance().format(Calendar.getInstance().getTime())+" "+scrivo);
//			newFile.write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
