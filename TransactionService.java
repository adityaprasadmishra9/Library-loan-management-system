package com.dbms.project_2341016436;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

public class TransactionService {

    public static void processLoan(
            int memberId,
            int bookId) {

        Connection con = null;

        try {

            con =
                    ConnectionManager.getConnection();

            /*
             * Disable auto commit
             * to manually control transaction
             */
            con.setAutoCommit(false);

            /*
             * Step 1:
             * Check whether book is available
             */
            String checkQuery =
                    "SELECT Available FROM Books " +
                    "WHERE BookID=?";

            PreparedStatement checkStmt =
                    con.prepareStatement(checkQuery);

            checkStmt.setInt(1, bookId);

            ResultSet rs =
                    checkStmt.executeQuery();

            if (rs.next()) {

                boolean available =
                        rs.getBoolean("Available");

                /*
                 * If book already issued
                 * rollback transaction
                 */
                if (!available) {

                    System.out.println(
                            "Book not available.");

                    con.rollback();

                    return;
                }
            }

            /*
             * Step 2:
             * Update book availability
             */
            String updateBook =
                    "UPDATE Books " +
                    "SET Available=false " +
                    "WHERE BookID=?";

            PreparedStatement ps1 =
                    con.prepareStatement(updateBook);

            ps1.setInt(1, bookId);

            ps1.executeUpdate();

            /*
             * Create savepoint
             */
            Savepoint sp =
                    con.setSavepoint();

            /*
             * Step 3:
             * Insert loan record
             */
            String insertLoan =
                    "INSERT INTO Loans(" +
                    "MemberID, BookID, LoanDate) " +
                    "VALUES(?,?,CURRENT_DATE)";

            PreparedStatement ps2 =
                    con.prepareStatement(insertLoan);

            ps2.setInt(1, memberId);
            ps2.setInt(2, bookId);

            ps2.executeUpdate();

            /*
             * Step 4:
             * Increase member active loan count
             */
            String updateMember =
                    "UPDATE Members " +
                    "SET ActiveLoans = ActiveLoans + 1 " +
                    "WHERE MemberID=?";

            PreparedStatement ps3 =
                    con.prepareStatement(updateMember);

            ps3.setInt(1, memberId);

            ps3.executeUpdate();

            /*
             * Commit transaction
             */
            con.commit();

            System.out.println(
                    "Loan processed successfully.");

        } catch (Exception e) {

            try {

                /*
                 * Rollback if any failure occurs
                 */
                if (con != null) {

                    con.rollback();

                    System.out.println(
                            "Transaction rolled back.");
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }
}

