# 💰 WalletWise: Smart Finance Manager

**WalletWise** is a personal finance management system developed using **Spring Boot** and **MySQL**. It allows users to track their income and expenses, set monthly budgets, and manage their financial goals with a clean and simple user interface.

---

## 🔧 Features

- 🧑‍💼 User Registration & JWT-Based Login
- 📥 Add, Edit & Delete Income/Expense Transactions
- 📊 Budget Management & Monthly Overspending Alerts
- 📁 View Transaction History (with filtering options)
- 🖥️ Basic Frontend in HTML, CSS & JavaScript
- 🔐 Secured Backend with Spring Security + JWT
- 💾 MySQL Integration with JPA & Hibernate

---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot, Spring Data JPA, Spring Security
- **Authentication:** JWT (JSON Web Tokens)
- **Database:** MySQL
- **Frontend:** HTML, CSS, JavaScript (Vanilla)
- **Build Tool:** Maven

---

## 🚀 Getting Started

Follow these steps to run the project locally on your system.

### ✅ Prerequisites

- Java 17+
- Maven
- MySQL installed and running
- (Optional) Postman or Swagger for API testing

### ⚙️ Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Ankit7832/walletwise-smart-finance-manager.git
   cd walletwise-smart-finance-manager
   
2. **Create MySQL Database**
   ```sql
   CREATE DATABASE walletwise_db;
   
3. **Configure Database in `application.properties`**  
   Open the file located at `src/main/resources/application.properties` and update the database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/walletwise_db
   spring.datasource.username=YOUR_DB_USERNAME
   spring.datasource.password=YOUR_DB_PASSWORD

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   
4. **Run the Spring Boot Application**

From the root directory of the project, run the following command:

    ### On Mac/Linux:
    ./mvnw spring-boot:run
    ### Or, if you're on Windows:
    mvnw.cmd spring-boot:run


