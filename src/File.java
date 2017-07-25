import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;



public class File {

public void getElementsFileToList(DefaultListModel<String> listModel,String fileName){
	
ArrayList<String> taskList= readFile(fileName);

for (String string : taskList) {
	listModel.addElement(string);
	
}
}

public void updateTaskFile(DefaultListModel<String> listModel,String fileName) throws IOException{
	clearFile(fileName);
	
	
	for(int i=0;i<listModel.size();i++){
		saveToFile(listModel.getElementAt(i),fileName);
	}
	
}

private  ArrayList<String> readFile(String filename){

	
	FileReader fr = null;
	BufferedReader br = null;
	ArrayList<String> fileArray = new ArrayList<String>();
	
	try
	{
		
		
		fr = new FileReader(filename);
		br = new BufferedReader(fr);
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			fileArray.add(sCurrentLine);
		}
		
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	
	finally 
	{

		try 
		{

			if (br != null)
				br.close();

			if (fr != null)
				fr.close();

		} 
		catch (IOException ex) 
		{

			ex.printStackTrace();

		}
	}
	
    return fileArray;
}

private  boolean isFileEmpty(String filename) throws IOException{

FileReader fr = new FileReader(filename);
BufferedReader br = new BufferedReader(fr);
String test=null;
try{ test=br.readLine();}
catch(IOException e)
{
	e.printStackTrace();
}
br.close();
if(test != null)
return true;
else return false;
}

void saveToFile(String value,String filename) throws IOException 
{
if(isFileEmpty(filename)) value="\n"+value;

BufferedWriter bw = null;
FileWriter fw = null;

fw = new FileWriter(filename, true);
bw = new BufferedWriter(fw);
bw.write(value);
bw.close();
}

void clearFile(String patch) throws FileNotFoundException{
PrintWriter writer = new PrintWriter(patch);
writer.print("");
writer.close();
}

}
