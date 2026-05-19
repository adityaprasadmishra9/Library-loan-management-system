package com.dbms.project_2341016436;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BusinessLogic {

    /*
     * Add new member
     */
    public static void addMember(String name) {

        String sql =
                "INSERT INTO Members(Name, ActiveLoans) " +
                "VALUES(?,0)";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, name);

            ps.executeUpdate();

            System.out.println(
                    "Member added successfully.");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /*
     * Add new book
     */
    public static void addBook(
            String title,
            String isbn) {

        String sql =
                "INSERT INTO Books(Title, ISBN, Available) " +
                "VALUES(?,?,true)";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, title);
            ps.setString(2, isbn);

            ps.executeUpdate();

            System.out.println(
                    "Book added successfully.");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    /*
     * Display all books
     */
    public static void showBooks() {

        String sql =
                "SELECT * FROM Books";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                java.sql.ResultSet rs =
                        ps.executeQuery()
        ) {

            System.out.println(
                    "\n===== BOOK LIST =====");

            while (rs.next()) {

                System.out.println(
                        "Book ID : "
                        + rs.getInt("BookID"));

                System.out.println(
                        "Title   : "
                        + rs.getString("Title"));

                System.out.println(
                        "ISBN    : "
                        + rs.getString("ISBN"));

                System.out.println(
                        "Available : "
                        + rs.getBoolean("Available"));

                System.out.println(
                        "----------------------");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    /*
     * Return issued book
     */
    public static void returnBook(int bookId) {

        try (
                Connection con =
                        ConnectionManager.getConnection()
        ) {

            /*
             * Make book available again
             */
            String updateBook =
                    "UPDATE Books " +
                    "SET Available=true " +
                    "WHERE BookID=?";

            PreparedStatement ps1 =
                    con.prepareStatement(updateBook);

            ps1.setInt(1, bookId);

            ps1.executeUpdate();

            System.out.println(
                    "Book returned successfully.");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    /*
     * Display all members
     */
    public static void showMembers() {

        String sql =
                "SELECT * FROM Members";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                java.sql.ResultSet rs =
                        ps.executeQuery()
        ) {

            System.out.println(
                    "\n===== MEMBER LIST =====");

            while (rs.next()) {

                System.out.println(
                        "Member ID : "
                        + rs.getInt("MemberID"));

                System.out.println(
                        "Name      : "
                        + rs.getString("Name"));

                System.out.println(
                        "Active Loans : "
                        + rs.getInt("ActiveLoans"));

                System.out.println(
                        "----------------------");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    /*
     * Display all loans
     */
    public static void showLoans() {

        String sql =
                "SELECT * FROM Loans";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                java.sql.ResultSet rs =
                        ps.executeQuery()
        ) {

            System.out.println(
                    "\n===== LOAN LIST =====");

            while (rs.next()) {

                System.out.println(
                        "Loan ID : "
                        + rs.getInt("LoanID"));

                System.out.println(
                        "Member ID : "
                        + rs.getInt("MemberID"));

                System.out.println(
                        "Book ID : "
                        + rs.getInt("BookID"));

                System.out.println(
                        "Loan Date : "
                        + rs.getDate("LoanDate"));

                System.out.println(
                        "----------------------");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}