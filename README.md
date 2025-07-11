# **UI Automation using Selenium Web Driver**

## Tech Stack Used


Java, Selenium WebDriver – For browser automation

Maven – Build tool for managing dependencies

TestNG, AssertJ – For organizing and asserting test results

Apache POI – For reading data from Excel files

Page Object Model with Page Factory – To structure and maintain page interactions

Log4j – For logging test steps and errors

Extent Reports – For rich, customizable HTML reports

Data Provider – Used to drive test cases with Excel data

Jenkins – For continuous integration

Docker + Selenium Grid – For parallel and distributed test execution

## Project Details
This project automates key functionalities of the Tutorials Ninja Demo E-commerce Website using a robust and scalable hybrid framework.

🔗 Application Under Test:
👉 https://tutorialsninja.com/demo/index.php?route=account/login

## Automated Test Cases
Login Page

Valid login

Invalid login scenarios

Excel-driven login test data

Registration Page

New user registration

Field validation

## Framework Used
Hybrid Framework includes:

Modular design using Page Object Model (POM)

Efficient element handling via Page Factory

Data-driven testing using Excel + TestNG @DataProvider

Utilities for logging, configuration, and WebDriver setup

## Data Management
Test data is stored in Excel files (.xlsx) and accessed via Apache POI

config.properties file holds environment variables like browser and URLs

DataProvider fetches test cases dynamically from Excel

## Reporting
Integrated with Extent Reports for visually rich, detailed test results

Log4j for step-by-step logging

Optional screenshot capture on failures

##  Selenium Grid + Docker Support
Dockerized Selenium Grid with Hub and Chrome/Firefox nodes

Tests can be executed remotely using RemoteWebDriver

