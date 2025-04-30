package advancedtaxsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<TaxDeclaration> declarations = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("===== Welcome to RRA Tax Management System =====");

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Declare PAYE");
            System.out.println("2. Declare VAT");
            System.out.println("3. Declare Withholding Tax");
            System.out.println("4. View Compliance Reports");
            System.out.println("5. Print Tax Receipts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    declarePAYE();
                    break;
                case "2":
                    declareVAT();
                    break;
                case "3":
                    declareWithholding();
                    break;
                case "4":
                    viewComplianceReports();
                    break;
                case "5":
                    printReceipts();
                    break;
                case "6":
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }

    private static void declarePAYE() {
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("\n--- PAYE Declaration ---");

                System.out.print("Enter Declaration ID: ");
                String id = scanner.nextLine();

                System.out.print("Enter Taxpayer Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Taxpayer TIN (9 digits): ");
                String tin = scanner.nextLine();
                if (!tin.matches("\\d{9}")) {
                    System.out.println("Invalid TIN format! Please enter a valid 9-digit TIN.");
                    continue;
                }

                System.out.print("Enter Gross Salary: ");
                double salary = Double.parseDouble(scanner.nextLine());
                if (salary <= 0) {
                    System.out.println("Salary must be positive! Please enter a valid salary.");
                    continue;
                }

                LocalDate date = LocalDate.now();

                PayedDeclaration paye = new PayedDeclaration(id, name, tin, date, salary);
                paye.validateDeclaration();
                paye.calculateTax();
                declarations.add(paye);

                System.out.println("PAYE Declaration submitted successfully.");
                valid = true;

            } catch (Exception e) {
                System.out.println("Error in PAYE Declaration: " + e.getMessage());
            }
        }
    }

    private static void declareVAT() {
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("\n--- VAT Declaration ---");

                System.out.print("Enter Declaration ID: ");
                String id = scanner.nextLine();

                System.out.print("Enter Taxpayer Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Taxpayer TIN (9 digits): ");
                String tin = scanner.nextLine();
                if (!tin.matches("\\d{9}")) {
                    System.out.println("Invalid TIN format! Please enter a valid 9-digit TIN.");
                    continue;
                }

                System.out.print("Enter Taxable Sales: ");
                double sales = Double.parseDouble(scanner.nextLine());

                System.out.print("Enter Purchases: ");
                double purchases = Double.parseDouble(scanner.nextLine());

                if (sales <= purchases) {
                    System.out.println("Sales must be greater than Purchases! Please enter valid values.");
                    continue;
                }

                LocalDate date = LocalDate.now();

                VATDeclaration vat = new VATDeclaration(id, name, tin, date, sales, purchases);
                vat.validateDeclaration();
                vat.calculateTax();
                declarations.add(vat);

                System.out.println("VAT Declaration submitted successfully.");
                valid = true;

            } catch (Exception e) {
                System.out.println("Error in VAT Declaration: " + e.getMessage());
            }
        }
    }

    private static void declareWithholding() {
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("\n--- Withholding Tax Declaration ---");

                System.out.print("Enter Declaration ID: ");
                String id = scanner.nextLine();

                System.out.print("Enter Taxpayer Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Taxpayer TIN (9 digits): ");
                String tin = scanner.nextLine();
                if (!tin.matches("\\d{9}")) {
                    System.out.println("Invalid TIN format! Please enter a valid 9-digit TIN.");
                    continue; // Retry input
                }

                System.out.print("Enter Category (Rent, Dividends, Services): ");
                String category = scanner.nextLine();

                System.out.print("Enter Payment Amount: ");
                double amount = Double.parseDouble(scanner.nextLine());

                if (amount <= 0) {
                    System.out.println("Amount must be positive! Please enter a valid amount.");
                    continue; // Retry input
                }

                LocalDate date = LocalDate.now();

                WithholdingTaxDeclaration wht = new WithholdingTaxDeclaration(id, name, tin, date, category);
                wht.validateDeclaration();
                wht.calculateTax();
                declarations.add(wht);

                System.out.println("Withholding Tax Declaration submitted successfully.");
                valid = true; // End the loop

            } catch (Exception e) {
                System.out.println("Error in Withholding Tax Declaration: " + e.getMessage());
            }
        }
    }

    private static void viewComplianceReports() {
        System.out.println("\n--- Compliance Reports ---");
        for (TaxDeclaration decl : declarations) {
            System.out.println(decl.toString());
            if (!decl.isPaid()) {
                System.out.println("! Not Paid");
            }
        }
    }

    private static void printReceipts() {
        System.out.println("\n--- Printing Tax Receipts ---");
        for (TaxDeclaration decl : declarations) {
            decl.generateReceipt();
        }
    }
}
