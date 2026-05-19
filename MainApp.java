package com.dbms.project_2341016436;

public class MainApp {

	public static void main(String[] args) {

	    DatabaseSetup.initializeDatabase();

	    java.util.Scanner sc =
	            new java.util.Scanner(System.in);

	    int choice;

	    do {

	        System.out.println(
	                "\n===== LIBRARY MENU =====");

	        System.out.println(
	                "1. Add Member");

	        System.out.println(
	                "2. Add Book");

	        System.out.println(
	                "3. Show Books");

	        System.out.println(
	                "4. Process Loan");

	        System.out.println(
	                "5. Return Book");
	        System.out.println(
	                "6. Run Benchmark");
	        System.out.println(
	                "7. Show Members");
	        System.out.println(
	                "8. Show Loans");

	        System.out.println(
	                "0. Exit");

	        System.out.print(
	                "Enter Choice: ");

	        choice = sc.nextInt();

	        sc.nextLine();

	        switch (choice) {

	            case 1:

	                System.out.print(
	                        "Enter Member Name: ");

	                String memberName =
	                        sc.nextLine();

	                BusinessLogic.addMember(
	                        memberName);

	                break;

	            case 2:

	                System.out.print(
	                        "Enter Book Title: ");

	                String title =
	                        sc.nextLine();

	                System.out.print(
	                        "Enter ISBN: ");

	                String isbn =
	                        sc.nextLine();

	                BusinessLogic.addBook(
	                        title,
	                        isbn);

	                break;

	            case 3:

	                BusinessLogic.showBooks();

	                break;

	            case 4:

	                System.out.print(
	                        "Enter Member ID: ");

	                int memberId =
	                        sc.nextInt();

	                System.out.print(
	                        "Enter Book ID: ");

	                int bookId =
	                        sc.nextInt();

	                TransactionService.processLoan(
	                        memberId,
	                        bookId);

	                break;

	            case 5:

	                System.out.print(
	                        "Enter Book ID: ");

	                int returnBookId =
	                        sc.nextInt();

	                BusinessLogic.returnBook(
	                        returnBookId);

	                break;
	            case 6:

	                PerformanceEvaluator.runBenchmarks();

	                break;
	            case 7:

	                BusinessLogic.showMembers();

	                break;
	            case 8:

	                BusinessLogic.showLoans();

	                break;

	            case 0:

	                ConnectionManager.shutdown();

	                System.out.println(
	                        "Exiting application.");

	                break;

	            default:

	                System.out.println(
	                        "Invalid choice.");
	        }

	    } while (choice != 0);
	}
}
