package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderGenerator {
    public static final String DATA_SEPARATOR = ";";
    public static final String NULL_VALUE = "null";
    
    public static Object[][] getData(File file) throws IOException{
    	
        List<String> fileData = null;
        try{
            fileData = readDataFromFile(file);
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
        
        int dataCountInRow = -1;
        
        String[][] parsedData = new String[fileData.size()][];
        for(int i = 0; i < fileData.size(); i++){
            parsedData[i] = fileData.get(i).split(DATA_SEPARATOR);
            
            if(dataCountInRow == -1){
            	dataCountInRow = parsedData[i].length;
            }else if(dataCountInRow != parsedData[i].length){
            	throw new IOException("Corrupted data file. Each line of file should contain " + 
            			"same number of data separated by " + DATA_SEPARATOR + ". Corrupted line: " + i);
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
    	StringBuilder fileContent = new StringBuilder();
        
        BufferedReader br = new BufferedReader(new FileReader(file));       
        while ((line = br.readLine()) != null) {            
            if(skippedFirstLine){
            	lines.add(line);
            } 
            skippedFirstLine = true;
        }     
        
        return lines;
    }
}
