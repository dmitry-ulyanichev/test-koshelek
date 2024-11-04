# Koshelek.ru Negative Signup Test Suite

This project is a Selenium + Cucumber test suite designed to validate the negative scenarios of the signup process on [koshelek.ru](https://koshelek.ru/). The purpose is to verify that invalid data and edge cases are handled correctly by the website’s signup functionality, ensuring users are provided with appropriate error messages and validations.

The test cases are written in Gherkin language and located in the `resources/features/signup_negative.feature` file.

---

## Table of Contents

- [Requirements](#requirements)
- [Setup Instructions](#setup-instructions)
- [Running the Test Cases](#running-the-test-cases)
- [Project Structure](#project-structure)
- [Contributing](#contributing)

---

## Requirements

To run this project, ensure you have the following installed:

- **Java SDK 20**: Download and install from [Oracle’s website](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html) or [OpenJDK](https://openjdk.org/).
- **Maven 3.8.8 or higher**: Install from the [Apache Maven website](https://maven.apache.org/download.cgi).
- **Browser driver**: ChromeDriver for Chrome. The driver version should match your installed browser version. Refer to the documentation below:
    - [ChromeDriver](https://sites.google.com/chromium.org/driver/)

**Note**: You may need to add your browser driver to your system's PATH environment variable or specify its location in the code.

---

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone <https://github.com/dmitry-ulyanichev/test-koshelek.git>
   cd <test-koshelek>