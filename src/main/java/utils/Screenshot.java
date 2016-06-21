package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	private static volatile Screenshot instance = null;
	protected static final Logger LOGGER = Logger.getLogger("");
	
	private String screenshotFolder;
	private String testOutputFolder;
	
	private Screenshot(){
		init();
	}
	
	public void init(){
		screenshotFolder = "screenshots";
		testOutputFolder = "test-output";
	}
	
	public static Screenshot getInstance(){
		if(instance == null){
			synchronized(Screenshot.class){
				instance = new Screenshot();
			}
		}
		return instance;
	}
		
	public void clearScrrenshotFolder(){
		new File(testOutputFolder).mkdir();
		File scrrenshotDirectory = new File(testOutputFolder + "/" + screenshotFolder + "/");
		scrrenshotDirectory.mkdir();
		
		try {
			FileUtils.cleanDirectory(scrrenshotDirectory);
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Failed to clean scrrenshots directory", e);
		}
	}
	
	public void takeScreenshot(WebDriver driver, String screenshotName){
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);        		
		
		new File(testOutputFolder).mkdir();
		File scrrenshotDirectory = new File(testOutputFolder + "/" + screenshotFolder + "/");
		scrrenshotDirectory.mkdir();
		
		File destFile = new File(testOutputFolder + "/" + screenshotFolder + "/" + screenshotName + ".png");
		
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(scrFile);
	        os = new FileOutputStream(destFile);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    }catch(Exception ex){
	    	LOGGER.log(Level.WARNING, "Failed to save screenshot", ex);
	    }
	    finally {
	    	try{
	    		is.close();
	 	        os.close();
	    	}catch(Exception ex){
	    		LOGGER.log(Level.WARNING, "Failed to close input/ouptut stream while saving screenshot", ex);
	    	}
	       
	    }
	}

	public void setScreenshotFolderName(String screenshotFolderName){
		this.screenshotFolder = screenshotFolderName;
	}
	
	public void setTestOutputFolderName(String testOutputFolderName){
		this.testOutputFolder = testOutputFolderName;
	}
}
