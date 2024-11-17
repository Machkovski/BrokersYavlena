# BrokersYavlena

This project is a Java-based web automation framework that uses Selenium for browser automation and TestNG for testing. The framework includes features for effective test management, reporting with Allure, and seamless ChromeDriver management using WebDriverManager.

***Features***

Java 17: Built with Java 17 to leverage the latest language features and improvements.

Selenium: Automates web browsers for robust UI testing.

TestNG: Provides a comprehensive framework for writing and executing tests.

Allure Reporting: Generates detailed and interactive test reports.

Cucumber Integration: Supports BDD (Behavior-Driven Development) style testing.

WebDriverManager: Automatically manages browser drivers, including ChromeDriver.

Lombok: Simplifies boilerplate code like getters and setters.


***Prerequisites***

Before running the project, ensure the following tools and software are installed on your system:

1. Java Development Kit (JDK) 17
    Download and install the latest JDK 17 from the Oracle website or OpenJDK.

2. Apache Maven
    Download and install Maven from Maven Downloads. Ensure Maven is added to your system's PATH.

3. IDE (e.g., IntelliJ IDEA, Eclipse)
    Use an integrated development environment for ease of development and debugging.

4. Browser
    Ensure you have the latest version of Google Chrome installed.

   
5. Allure Command-Line Tool (for generating and viewing reports)
   
    Install Allure CLI via Homebrew (macOS) or Scoop (Windows):
   
        brew install allure  #forMacOS
   
        scoop install allure #forWindows
   
    Alternatively, download it from the official Allure GitHub page.

7. Git
   
    Ensure Git is installed for version control and repository management.

***Dependencies***

The project dependencies are managed via Maven and specified in the pom.xml file. Key dependencies include:

    Selenium (4.20.0): Core library for browser automation.

    WebDriverManager (5.8.0): Handles browser driver management.

    TestNG (7.7.1): Provides test configuration and execution framework.

    Lombok (1.18.32): Simplifies Java code with annotations.

    Cucumber (7.11.1): Enables BDD-style test development.

    Allure-TestNG (2.27.0): Integrates Allure reporting with TestNG.


***Setup Instructions***
1. Clone the Repository
   
   Clone the project to your local machine using Git.

3. Install Maven Dependencies
   
    Run the following command to download all dependencies specified in the pom.xml file:
   
       mvn clean install

5. Run Tests
   
    Execute tests using Maven via "mvn tests" or via the Run option

7. Generate Allure Report
   
    After running the tests, generate and open the Allure report:
   
        allure serve target/allure-results

***Key Notes***

Driver Management: The WebDriverManager library automates ChromeDriver setup, so no manual driver downloads are necessary.

Reporting: Test results are saved in the target/allure-results directory, which can be visualized using the Allure CLI.

Cucumber Integration: Define feature files and step definitions for BDD scenarios.

