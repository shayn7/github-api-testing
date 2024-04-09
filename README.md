Introduction
This project is an api automation project for testing github.com


Requirements
Make sure you have JDK17, maven, and allure installed in your system

Running the project from the terminal
1. clone the project
2. open the terminal and navigate to the project location
3. run the command: mvn clean test (it will run the tests configured on testng.xml file by default)

You can also run the project with specific parameters if you wish to use different browsers, URLs, etc.
The parameters supported are: 
suiteXmlFile - the xml file of the desired tests (repository_creation_tests.xml, etc.)


The command to use with any of the parameters above for example is:
mvn clean test -DsuiteXmlFile=suite_files/create_repository_tests.xml


In order to generate the report:
1. enter the terminal
2. navigate to the project location
3. enter the command: allure serve allure-results (allure path must be added to the global variables or else run this command by entering the path of allure in your system )





