package com.dbms.project_2341016436;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PerformanceEvaluator {

    /*
     * Run all benchmarks
     */
    public static void runBenchmarks() {

        System.out.println(
                "\n===== PERFORMANCE TEST =====");

        normalInsertBenchmark();

        batchInsertBenchmark();
    }

    /*
     * Normal insert benchmark
     */
    public static void normalInsertBenchmark() {

        Connection con = null;

        try {

            con =
                    ConnectionManager.getConnection();

            con.setAutoCommit(false);

            String sql =
                    "INSERT INTO Members(Name, ActiveLoans) " +
                    "VALUES(?,0)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            long start =
                    System.nanoTime();

            for (int i = 1; i <= 1000; i++) {

                ps.setString(
                        1,
                        "NormalUser" + i);

                ps.executeUpdate();
            }

            con.commit();

            long end =
                    System.nanoTime();

            double timeMs =
                    (end - start) / 1000000.0;

            System.out.println(
                    "\nNORMAL INSERT:");

            System.out.println(
                    "1000 Records Inserted");

            System.out.println(
                    "Execution Time: "
                    + timeMs
                    + " ms");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (con != null) {

                    con.close();
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }

    /*
     * Batch insert benchmark
     */
    public static void batchInsertBenchmark() {

        Connection con = null;

        try {

            con =
                    ConnectionManager.getConnection();

            con.setAutoCommit(false);

            String sql =
                    "INSERT INTO Members(Name, ActiveLoans) " +
                    "VALUES(?,0)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            long start =
                    System.nanoTime();

            for (int i = 1; i <= 1000; i++) {

                ps.setString(
                        1,
                        "BatchUser" + i);

                ps.addBatch();
            }

            ps.executeBatch();

            con.commit();

            long end =
                    System.nanoTime();

            double timeMs =
                    (end - start) / 1000000.0;

            System.out.println(
                    "\nBATCH INSERT:");

            System.out.println(
                    "1000 Records Inserted");

            System.out.println(
                    "Execution Time: "
                    + timeMs
                    + " ms");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (con != null) {

                    con.close();
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }
}