package test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Mailer {
	private static final String DOMAINS = "http://api.temp-mail.ru/request/domains/format/json";
	
	private String[] domains(){
		
	}
	
	
	
	
	
	 private static String getUrlSource(String url) throws IOException {
         URL yahoo = new URL(url);
         URLConnection yc = yahoo.openConnection();
         BufferedReader in = new BufferedReader(new InputStreamReader(
                 yc.getInputStream(), "UTF-8"));
         String inputLine;
         StringBuilder a = new StringBuilder();
         while ((inputLine = in.readLine()) != null)
             a.append(inputLine);
         in.close();

         return a.toString();
     }
}
