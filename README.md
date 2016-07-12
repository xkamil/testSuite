# testSuite
Project structure with support classes to write testNG + WebDriver tests

Consists classes:

DataProviderGenerator - generates data for @DataProvider from cvs file with stucture like:
header1;header2
data1_1;data_12
data2_1;data2_2
and returns object type Object

Screenshot - takes screenshot from web browser and saves it default in /test-output/screenshots folder

DataGenerator - used to generate random strings, numbers, etc.

