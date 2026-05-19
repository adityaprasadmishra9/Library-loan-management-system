# Library Loan Management System

A console-based Library Loan Management System developed using Java, JDBC, and Apache Derby database. The project demonstrates database connectivity, transaction management, CRUD operations, and JDBC performance benchmarking through a menu-driven application.

---

## Project Description

This application is designed to manage basic library activities such as:

- Member registration
- Book management
- Book issue and return
- Viewing member and loan records
- Transaction handling
- Performance comparison of database operations

The project mainly focuses on JDBC implementation, transaction processing, and database optimization techniques.

---

## Technologies Used

- Java
- JDBC
- Apache Derby Database
- SQL
- Eclipse IDE

---

## Features

- Add new members
- Add books to library
- Display all books
- Process book loans
- Return issued books
- Display member details
- Display loan records
- Benchmark normal insert vs batch insert
- Transaction management using commit and rollback

---

## Project Structure

### ConnectionManager.java
Handles JDBC database connection and shutdown operations.

### DatabaseSetup.java
Creates required database tables and initializes schema.

### BusinessLogic.java
Contains CRUD operations for books, members, and loan handling.

### TransactionService.java
Implements transaction management using:
- Auto commit control
- Commit
- Rollback
- Savepoints

### PerformanceEvaluator.java
Performs benchmarking and compares:
- Normal Insert
- Batch Insert

### MainApp.java
Provides menu-driven console interface for executing all operations.

---

## Database Tables

### Members Table
| Column Name | Data Type |
|------------|------------|
| MemberID | INT |
| Name | VARCHAR |
| ActiveLoans | INT |

### Books Table
| Column Name | Data Type |
|------------|------------|
| BookID | INT |
| Title | VARCHAR |
| ISBN | VARCHAR |
| Available | BOOLEAN |

### Loans Table
| Column Name | Data Type |
|------------|------------|
| LoanID | INT |
| MemberID | INT |
| BookID | INT |
| LoanDate | DATE |
| ReturnDate | DATE |

---

## JDBC Connection Example

```java
private static final String URL =
    "jdbc:derby:libraryDB;create=true";

public static Connection getConnection()
        throws SQLException {

    return DriverManager.getConnection(URL);
}
```

This module establishes connection between Java application and Apache Derby database.

---

## Transaction Management Example

```java
con.setAutoCommit(false);

con.commit();

con.rollback();
```

Loan operations are executed using transaction management to maintain data consistency and integrity.

---

## Performance Benchmarking

The project compares execution time between:

1. Normal Insert
2. Batch Insert

Benchmark results show that batch insert operations execute faster than normal inserts due to reduced database communication overhead.

---

## Workflow of the System

1. User selects operation from console menu
2. JDBC establishes database connection
3. SQL queries execute using PreparedStatement
4. Transaction operations commit or rollback
5. Results display through console interface

---

## Sample Operations

- Add Member
- Add Book
- Process Loan
- Return Book
- Show Members
- Show Loans
- Run Performance Benchmark

---

## How to Run the Project

1. Open project in Eclipse IDE
2. Ensure JDK and Apache Derby are configured
3. Run `MainApp.java`
4. Use menu-driven console to perform operations

---

## Output

The application successfully performs:
- CRUD operations
- JDBC connectivity
- Transaction processing
- Database consistency handling
- Performance benchmarking

Batch insert operations showed significantly better execution time compared to normal insert operations.

---

## Future Enhancements

- GUI implementation using Java Swing or JavaFX
- Login authentication system
- Fine calculation module
- Search and filter functionality
- Multi-user support
