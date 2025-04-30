package advancedtaxsystem;

import java.time.LocalDate;

public class WithholdingTaxDeclaration extends TaxDeclaration {
    private String category; // e.g., "Rent", "Services", "Dividends"

    public WithholdingTaxDeclaration(String declarationId, String taxpayerName, String taxpayerTIN, LocalDate declarationDate, String category) {
        super(declarationId, taxpayerName, taxpayerTIN, declarationDate);
        this.category = category;
        validateDeclaration();
    }

    @Override
    public double calculateTax() {
        double rate;
        switch (category.toLowerCase()) {
            case "services":
                rate = 0.15;
                break;
            case "rent":
                rate = 0.10;
                break;
            case "dividends":
                rate = 0.05;
                break;
            default:
                throw new IllegalArgumentException("Invalid category.");
        }
        taxAmount = 10000 * rate;
        return taxAmount;
    }

    @Override
    public void validateDeclaration() {
        if (!category.equalsIgnoreCase("services") && !category.equalsIgnoreCase("rent") && !category.equalsIgnoreCase("dividends")) {
            throw new IllegalArgumentException("Invalid withholding tax category.");
        }
        if (declarationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Declaration date cannot be in the future.");
        }
    }

    @Override
    public void generateReceipt() {
        System.out.println("\n--- Withholding Tax Receipt ---");
        System.out.println("Taxpayer: " + taxpayerName + " (" + taxpayerTIN + ")");
        System.out.println("WHT Category: " + category);
        System.out.println("Tax Due: $" + taxAmount);
        System.out.println("Payment Status: " + (isPaid ? "PAID" : "NOT PAID"));
    }

    @Override
    public void enforceCompliance() {
        if (!isPaid) {
            System.out.println("⚠ Penalty Applied: Withholding Tax unpaid.");
            taxAmount *= 1.20;
        }
    }
}
