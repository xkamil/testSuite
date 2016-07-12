package utils.mailer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import utils.DataGenerator;


//TODO
public class Mailer {
	private static final Logger LOGGER = Logger.getLogger("");
	private static final String DOMAINS = "http://api.temp-mail.ru/request/domains/format/json";

	public Mailer(){
		
	}
	
	public void init(){
		String mailLocalPart = DataGenerator.getRandomString(DataGenerator.ENGLISH_LETTERS, 10);
		String mailDomainPart = getRandomDomain();
		String mail = mailLocalPart + "@" + mailDomainPart;
	}
	
	private static String getUrlSource(String url) {
		try {
			URL yahoo = new URL(url);
			URLConnection yc = yahoo.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
			in.close();

			return a.toString();
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "Error while reading avaible mail domains from: " + DOMAINS, ex);
			return "";
		}

	}

	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	public String[] getAvaibleDomains() {
		String json = getUrlSource(DOMAINS);
		Gson gson = new Gson();
		String[] domains = gson.fromJson(json, String[].class);
		return domains;
	}

	public String getRandomDomain() {
		String[] domains = getAvaibleDomains();
		Random rnd = new Random();
		String domain = domains[rnd.nextInt(domains.length)];
		return domain;
	}

	public static void main(String... args) throws Exception {
		Mailer mailer = new Mailer();
		String domain = mailer.getRandomDomain();
		System.out.println("Random domain: " + domain);
		System.out.println(MD5("kamilwrobel" + domain));
	}
}
