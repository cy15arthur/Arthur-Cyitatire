package advancedtaxsystem;

import java.time.LocalDate;

public class PayedDeclaration extends TaxDeclaration {
    private double grossSalary;

    public PayedDeclaration(String declarationId, String taxpayerName, String taxpayerTIN, LocalDate declarationDate, double grossSalary) {
        super(declarationId, taxpayerName, taxpayerTIN, declarationDate);
        this.grossSalary = grossSalary;
        validateDeclaration();
    }

    @Override
    public double calculateTax() {
        if (grossSalary <= 0) return 0;
        // Simple example: 20% tax rate
        taxAmount = grossSalary * 0.20;
        return taxAmount;
    }

    @Override
    public void validateDeclaration() {
        if (grossSalary <= 0) {
            throw new IllegalArgumentException("Gross salary must be positive.");
        }
        if (declarationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Declaration date cannot be in the future.");
        }
    }

    @Override
    public void generateReceipt() {
        System.out.println("\n--- PAYE Tax Receipt ---");
        System.out.println("Taxpayer: " + taxpayerName + " (" + taxpayerTIN + ")");
        System.out.println("Tax Amount: $" + taxAmount);
        System.out.println("Payment Status: " + (isPaid ? "PAID" : "NOT PAID"));
    }

    @Override
    public void enforceCompliance() {
        if (!isPaid && declarationDate.plusMonths(1).plusDays(15).isBefore(LocalDate.now())) {
            System.out.println("! Penalty Applied: Late Payment for PAYE.");
            taxAmount *= 1.10; // 10% penalty
        }
    }
}
