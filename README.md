# testSuite
Test project simple framework
=============================


Project structure
-----------------

* /pages - pages extends AbstractPage class, implements PageObject pattern  
* /suites - testNG suites. Package consists 2 xml files that fire all tests (AllTests.xml)  
			 		   and only selected ones (SelectedTests.xml)  
* /tests - test classes extends AbstractTest class  
* /utils - utility classes   
		

Utility classes
---------------

**DataGenerator** - class that have only static method to generate random data like mail, numbers with speficied length, city codes, strings.

Example:  
`int phone = DataGenerator.getRandomNumber(9) // generates random phone number with 9 digits  `
`String email = DataGenerator.getRaondmEmail("example.com") // generates random email number with domain name example.com`

**DataProviderGenerator** - class reads data form csv file and parses it to Object[][] so it can be used by testNG DataProvider. 

Example:  
`Object[][] data = DataProviderGenerator.getData(new File("test-input/users.csv")) // reads file uses.csv and parse it to Object[][] `
**Warning! First line of csv file is ignored so it can be used to describe data in csv file**  



	

