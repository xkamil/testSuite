package tests;

import test1.DataGenerator;

public class DataGeneratorTest {

	public static void main(String[] args) {
		String email = DataGenerator.getRandomEmail(null);
		String name = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String cityCode = DataGenerator.getRandomCityCode("xx-xx-xx--x", '-');
		int phone = DataGenerator.getRandomNumber(9);
		
		System.out.println("Email: " + email);
		System.out.println("Name: " + name);
		System.out.println("cityCode: " + cityCode);
		System.out.println("phone: " + phone);

	}

}
