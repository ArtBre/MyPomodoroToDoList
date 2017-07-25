import javax.swing.JTextField;
import java.io.*;
import sun.audio.*;

public class SuppFunct {

	static long precTimeToMs(int hours,int minutes,int seconds){
		long h= Integer.toUnsignedLong(hours);
		long m= Integer.toUnsignedLong(minutes);
		long s= Integer.toUnsignedLong(seconds);
		
		long result=(h*3600)+(m*60)+(s);
		
		return result;
	}

    static void nullToZero(JTextField textField){
    	if(textField.getText().isEmpty()==true) textField.setText("0");
    	try{Integer.parseInt(textField.getText());}
    	catch(NumberFormatException e){
    		textField.setText("0");
    	}
    }

static void playCycleSound(String path) throws IOException{
    	
        String gongFile = path;
        InputStream in = new FileInputStream(gongFile);

        
        AudioStream audioStream = new AudioStream(in);

        
        AudioPlayer.player.start(audioStream);    }





}

