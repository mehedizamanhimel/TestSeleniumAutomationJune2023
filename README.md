# TestSeleniumAutomationJune2023

A Selenium 4 + TestNG web automation framework using the Page Object Model (POM) pattern, with Extent Reports, Log4j2 logging, and externalized configuration.

## 🛠 Tech Stack

| Tool | Version |
|---|---|
| Java | 11 |
| Selenium | 4.20.0 |
| TestNG | 7.9.0 |
| WebDriverManager | 5.8.0 |
| Extent Reports | 5.1.1 |
| Log4j2 | 2.23.1 |
| Maven | 3.x |

## 📁 Project Structure

```
src/
├── main/java/com/samplesite/
│   ├── base/           → BaseTest.java (WebDriver lifecycle)
│   ├── pages/          → LoginPage.java (Page Object Model)
│   ├── utils/          → ConfigReader.java
│   └── listeners/      → ExtentReportListener.java
├── test/java/com/samplesite/
│   └── tests/          → LoginTest.java
└── test/resources/
    └── config.properties
```

## ⚙️ Configuration

All environment settings are managed in `src/test/resources/config.properties`:

```properties
browser=chrome
baseUrl=https://the-internet.herokuapp.com/login
valid.username=tomsmith
valid.password=SuperSecretPassword!
```

## ▶️ How to Run

```bash
# Run all tests via Maven
mvn clean test

# Run via TestNG XML directly
mvn test -DsuiteXmlFile=testng.xml
```

## 📊 Reports

After execution, the HTML report is generated at:
```
test-output/ExtentReport.html
```
Open it in any browser to view detailed pass/fail results per test.

## 🌐 Test Application

Tests run against [The Internet - Herokuapp](https://the-internet.herokuapp.com/login) — a publicly available practice site for automation.

## ✅ Test Coverage

- Successful login with valid credentials
- Login failure with invalid username
- Login failure with invalid password
- Login failure with empty username
- Login failure with empty password
