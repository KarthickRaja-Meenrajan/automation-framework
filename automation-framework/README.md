# QA Automation Framework – Genesys Assignment

## 📌 Overview

This project is a **Selenium + TestNG automation framework** built to cover UI and API test scenarios.

It follows **Page Object Model (POM)** design with reusable utilities, logging, and structured test execution.

---

## ⚙️ Tech Stack

* Java (1.8+)
* Selenium 3.141.59
* TestNG
* Apache HttpClient (API Testing)
* SLF4J (Logging)
* WebDriverManager

---

## 📁 Project Structure

```
com.genesys
 ├── base        → Driver setup & Base test
 ├── pages       → Page Object classes
 ├── tests       → Test cases (UI + API)
 ├── utils       → Wait, Config, JSON, Logger
 └── resources   → Test data (credential.json)
```

---

## ▶️ How to Run

1. Clone the repo
2. Import as Maven project
3. Run `testng.xml`

---

## ✅ Test Scenarios Covered

### Case 1 – Purchase Flow

* Login using JSON credentials
* Add items to cart
* Complete checkout
* Validate order success

---

### Case 2 – Login Error & Footer

* Validate error on empty login
* Login with valid user
* Verify footer contains **2026** and **Terms of Service**

---

### Case 3 – Rich Text Editor

* Enter formatted text:

  * **Automation** → Bold
  * **Test** → Underline
* Validate text in editor

---

### Case 4 – iFrame & Tab Handling

* Handle iframe interaction
* Switch between tabs
* Validate alert message
* Verify tooltip page

---

### Case 5 – REST API Testing

* Send GET request to `/users`
* Parse JSON response
* Log Name | Email
* Validate email format

---

## 🔧 Features

* Page Object Model (POM)
* Centralized Wait Utility
* Config-based URLs
* JSON test data handling
* Step-level logging
* Screenshot on failure
* Stable execution handling (PageLoadStrategy)

---

## 📊 Execution Result

```
Total tests: 5
Passed: 5
Failed: 0
```

---

## 📌 Notes

* Used `PageLoadStrategy.EAGER` to handle slow-loading pages
* Framework designed for easy scalability

---

## 🚀 Author

Karthick Raja PM
