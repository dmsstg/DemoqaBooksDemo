
## :mag_right: Demoqa Book Web App Test Automation Demo

### Description

Current repo contains project, designed to demonstrate my approaches to the `test framework development`, `graphical user interface (GUI) automated testing`, and `application programming interface (API) workflow automation` using the Demoqa Book Web Application as an example. Demoqa Books is an online shopping demo web application.

The current project focuses solely on executing straightforward happy path scenarios. Activities such as test planning, test strategy development, and the application of various white- and black-box testing techniques are beyond the scope of this demo project.

System Under Test link: https://demoqa.com/books

### Key features and details of the designed software testing framework
- Implementation of `Object-Oriented Programming (OOP)` concepts and principles to enhance source code readability , reusability , and maintainability .
- Leveraging of the `Project Object Model (POM)` to `simplify the project building process.
- Application of the `Page Object Model (POM)` to streamline test maintenance and reduce code redundancy.
- Utilization of clear and understandable `Gherkin Syntax` for describing test scenarios.
  > <font size=3.5>:round_pushpin:**Note:** The `Behavior-Driven Testing (BDD)` approach is implemented to foster `better communication with the business team`. </font>

- No Hardcoding! The framework includes `Data-Driven Testing (DDT)` capabilities.
  ><font size=3.5>:round_pushpin:**Note:**</font>
  >- <font size=3.5>`Scenario Outlines` and `Examples` are used for `test parametrization`.</font>
  >- <font size=3.5>`.properties files` serve as a source of `static test data`.</font>
  >- <font size=3.5> `Java Random` class generates `dynamic test data` (e.g., quantity of items) for specific test steps.</font>  

- Support for `end-to-end data validation within scenarios` is facilitated by employing Dependency Injection and ScenarioContext.
- Enables `multi-environment test execution` through the design of Environment and ConfigurationReader classes.
  > :round_pushpin:**Note:** The *environment* key-value in the *configuration.properties* file specifies the *.properties file* used for retrieving test data according to the specified test environment.
- The Driver class is implemented with the singleton design pattern and enables both `Cross-Browser testing` and `Distributed test execution`.
  > :round_pushpin:**Note:** The *browser'* key-value in the *configuration.properties* file enables both local and remote test runs. Support for `Selenium Grid` facilitates remote delegated test execution and ensures cross-browser testing.
- Automatic `rerun for failed tests` to minimize environment and connection issues.
- `Attachment of screenshots` for failed scenarios in generated reports.
- Utilization of `JSON Reporting` to upload test run results to the bug tracking system.
- Support for `parallel test scenario execution`.
- `Implementation of Plain Old Java Objects (POJO)` increasing the readability and re-usability of a program.
- `Custom Exception` to enhanced test run analysis and debugging.

### Framework Built With:
- **IntelliJ IDEA**: Integrated development environment.
- **Java Development Kit 11**: Software development environment.
- **Apache Maven**: Project management tool.
- **jUnit 4**: Testing framework used, in part, for assertion.
- **Selenium 4**: Browser automation tool.
- **WebDriverManager**: Selenium WebDriver browser driver management tool.
- **Cucumber 7**: Behavior Driven Development (BDD) tool.
- **Maven Surefire Plugin**: Test execution tool.
- **Cucumber Reporting**: Test report generator tool.
- **GitHub**: Version Control System.
- **Apache Log4j**: Logging tool.
- **REST Assured**: API REST services testing and validating tool.
- **Jackson Databind**: JSON format data processing tool.
- **Project Lombok**: Automatic Resource Management tool.
- **PicoContainer**: Dependency Injection tool
- **Java Faker**: Fake data generating tool.

### Project folder structure
- :file_folder: `DemoqaBooksDemo`: Root  folder  of  the  testing  project.
  - :file_folder: `src/test`: Test source code directory.
    - :file_folder: `src/test/java/com/demoqa/books`: Package structure organizing test code files.
      - :file_folder: `base`: Contains base test classes for common test framework functionalities.
        - `Driver.class` - manages browser instance launch and termination.
        - `PageProvider.java` - for managing the instantiation of page objects.
        - `ScenarioContext.java` - for handling end-to-end data validation.
        - `TestEnvironment.java` - to read and store test data.
      - :file_folder: `exception`: Houses custom exception classes.
      - :file_folder: `pages`: Stores Page Object Model (POM) classes representing web pages.
      - :file_folder: `pojos`: Stores Plain Old Java Objects (POJO) classes.
      - :file_folder: `runners`: Contains the Cucumber runner classes.
        -  `CucRunner.class` - for regular test running.
        - `ReRunner.class` - to run failed scenarios.
      - :file_folder: `step_defs`: Holds step definition classes mapping Gherkin scenarios to Java code.
      - :file_folder: `utils`: Stores utility classes with helper functions for reusable functionalities.
    - :file_folder: `src/test/resources`: Test resources folder with various test-related resources.
      - :file_folder: `environments`: Stores .properties files containing test data categorized by their relation to test environments.
      - :file_folder: `features`: Houses feature files written in Gherkin syntax, outlining test case scenarios.
      - `log4j2.properties` - properties file with Maven Log4j logging tool settings.
  - :file_folder: `pom.xml`: Maven Project Object Model file, containing project configurations and dependencies.
  - `configuration.properties` - properties file containing test execution settings.
  -  `cucumber-reporting.properties` - properties file with cucumber reporting tool settings.

### How to Install and Run the Project

#### Prequisites:
-  `Java 11.0.18 SDK` or later ([download](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html))
- `Java IDE` (IntelliJ IDEA as an exmple, [download](https://www.jetbrains.com/idea/download/))
- `Apache Maven` ([download](https://maven.apache.org/index.html))
- `Web Browser` (Google Chrome is more preferable, [download](https://www.google.com/chrome/))

#### Follow these steps:
1. Clone the project from this link https://github.com/dmsstg/DemoqaBooksDemo.git to your local repository using your IDE.
2. Run the project from your IDE's command prompt or terminal using this command: <code>mvn test</code>
4. Access the generated report in the following directory: `target/cucumber/cucumber-html-reports/overview-features.html`
   > :round_pushpin:**Note:** to configure the browser change the related values in the file by next path: <code>src/test/resources/configuration.properties</code>