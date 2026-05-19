package com.dbms.project_2341016436;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void initializeDatabase() {

        try (
                Connection con =
                        ConnectionManager.getConnection();

                Statement stmt =
                        con.createStatement()
        ) {

            stmt.executeUpdate(
                    "CREATE TABLE Members (" +
                    "MemberID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "Name VARCHAR(100), " +
                    "ActiveLoans INT DEFAULT 0)"
            );

            stmt.executeUpdate(
                    "CREATE TABLE Books (" +
                    "BookID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "Title VARCHAR(200), " +
                    "ISBN VARCHAR(50), " +
                    "Available BOOLEAN)"
            );

            stmt.executeUpdate(
                    "CREATE TABLE Loans (" +
                    "LoanID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "MemberID INT, " +
                    "BookID INT, " +
                    "LoanDate DATE, " +
                    "ReturnDate DATE, " +
                    "FOREIGN KEY (MemberID) REFERENCES Members(MemberID), " +
                    "FOREIGN KEY (BookID) REFERENCES Books(BookID))"
            );

            System.out.println(
                    "Database initialized successfully.");

        } catch (Exception e) {

            System.out.println(
                    "Tables may already exist.");
        }
    }
}

