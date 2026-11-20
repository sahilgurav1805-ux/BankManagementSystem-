# Bank Management System

![Java](https://img.shields.io/badge/Java-Swing-007396?logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/Connectivity-JDBC-555555)

A desktop banking application built with Java Swing, JDBC and MySQL. It simulates core banking operations through a graphical interface backed by a persistent MySQL database.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Concepts Demonstrated](#concepts-demonstrated)
- [Author](#author)

## Features

| Feature | Description |
|---|---|
| Login authentication | Users log in to access their account. |
| Account registration | New users can sign up for an account. |
| Deposit | Add money to the account. |
| Withdrawal | Withdraw money from the account. |
| Balance enquiry | View the current account balance. |
| Mini statement | View recent transactions. |
| PIN change | Update the account PIN. |
| Database integration | All data is stored in MySQL through JDBC. |

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| Swing / AWT | Graphical user interface |
| MySQL | Relational database |
| JDBC | Database connectivity |
| IntelliJ IDEA / Eclipse / NetBeans | Development environments |

## Project Structure

| File | Responsibility |
|---|---|
| `Login.java` | Login screen and authentication |
| `Signup.java` | Account registration |
| `Deposit.java` | Deposit transactions |
| `Withdraw.java` | Withdrawal transactions |
| `BalanceEnquiry.java` | Balance lookup |
| `MiniStatement.java` | Recent transaction history |
| `PinChange.java` | PIN update |
| `Conn.java` | MySQL database connection |

## Getting Started

### Prerequisites

- JDK installed
- MySQL installed and running
- A Java IDE (IntelliJ IDEA, Eclipse or NetBeans)
- MySQL Connector/J (JDBC driver) added to the project

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/sahilgurav1805-ux/Bank-Management-System.git
   ```

2. Open the project in your Java IDE.

3. Create the MySQL database and the required tables.

4. Update the database URL, username and password in `Conn.java`.

   > **Security note:** Do not commit your real database password to GitHub.

5. Run `Login.java` to start the application.

## Concepts Demonstrated

- Object-oriented programming
- GUI development with Swing
- Database connectivity with JDBC
- CRUD operations
- Event handling
- Modular project structure

## Author

**Sahil Gurav**

- GitHub: [sahilgurav1805-ux](https://github.com/sahilgurav1805-ux)
- LinkedIn: [sahilgurav18](https://www.linkedin.com/in/sahilgurav18/)
