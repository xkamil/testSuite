package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataProviderGenerator {
	public static final Logger LOGGER = Logger.getLogger("");
    public static final String DATA_SEPARATOR = ";";
    public static final String NULL_VALUE = "null";
    
    public static Object[][] getData(File file){
    	
        List<String> fileData = null;
        try{
            fileData = readDataFromFile(file);
        }catch(IOException ex){
        	LOGGER.log(Level.SEVERE, "Failed to read file " + file.getName(), ex);
            return null;
        }
        
        int dataCountInRow = -1;
        
        String[][] parsedData = new String[fileData.size()][];
        for(int i = 0; i < fileData.size(); i++){
            parsedData[i] = fileData.get(i).split(DATA_SEPARATOR);
            
            if(dataCountInRow == -1){
            	dataCountInRow = parsedData[i].length;
            }else if(dataCountInRow != parsedData[i].length){
            	String msg = "Corrupted data file. Each line of file should contain " + 
            			"same number of data separated by " + DATA_SEPARATOR + ". Corrupted line: " + i;
            	LOGGER.log(Level.SEVERE, msg);
            }
            
            //replaces null strings with null value
            for(int j = 0; j < parsedData[i].length; j++){
                parsedData[i][j] = parsedData[i][j].replace("null", "");
            }
        }       
        return parsedData;
    }

    private static List<String> readDataFromFile(File file) throws IOException {
    	boolean skippedFirstLine = false;
    	String line = "";
    	List<String> lines = new ArrayList<String>();
        
        BufferedReader br = new BufferedReader(new FileReader(file));       
        while ((line = br.readLine()) != null) {            
            if(skippedFirstLine){
            	lines.add(line);
            } 
            skippedFirstLine = true;
        }     
        br.close();
        return lines;
    }
}
