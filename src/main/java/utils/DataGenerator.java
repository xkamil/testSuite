package utils;

import java.util.Random;

public class DataGenerator {
	public static final String ENGLISH_LETTERS = "abcdefghijklmnopqrstuvwxyz";
	public static final String POLISH_LETTERS="a¹bcædeêfghijkl³mnñoóprsœtuwyzŸ¿";
	
	public static char getRandomCharacter(String letters){
		Random rnd = new Random();
		int index = rnd.nextInt(letters.length());
		return letters.charAt(index);
	}
	
	public static String getRandomString(String letters, int length){
		char[] charArray = new char[length];
		for(int i = 0; i < length; i++){
			charArray[i] = getRandomCharacter(letters);
		}
		return new String(charArray);
	}
	
	public static String getRandomEmail(String domainPart){
		if(domainPart == null){
			domainPart = "example.com";
		}
		return getRandomString(ENGLISH_LETTERS,8) + "@" + domainPart;
	}
	
	public static int getRandomNumber(int length){
		StringBuilder strBuilder = new StringBuilder();
		int firstNumber = 0;
		Random rnd = new Random();
		while(firstNumber == 0){
			firstNumber = rnd.nextInt(10);
		}
		strBuilder.append(firstNumber);
		for(int i = 1 ; i < length; i++){
			strBuilder.append(rnd.nextInt(10));
		}
		return Integer.valueOf(strBuilder.toString());
	}
	
	/**
	 * 
	 * @param pattern - sample pattern xx-xxx
	 * @param separator - separator is a char that will not be replaced in pattern
	 * @return - city code contains random numbers and separator if supplied in pattern
	 */
	public static String getRandomCityCode(String pattern, char separator){
		StringBuilder strBuilder = new StringBuilder();
		Random rnd = new Random();
		for(int i = 0; i < pattern.length(); i++ ){
			if(pattern.charAt(i) != separator){
				strBuilder.append(rnd.nextInt(10));
			}else{
				strBuilder.append(separator);
			}
		}
		return strBuilder.toString();
	}
}
