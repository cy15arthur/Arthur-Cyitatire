package advancedtaxsystem;

import java.time.LocalDate;

public class VATDeclaration extends TaxDeclaration {
    private double taxableSales;
    private double taxablePurchases;

    public VATDeclaration(String declarationId, String taxpayerName, String taxpayerTIN, LocalDate declarationDate, double sales, double purchases) {
        super(declarationId, taxpayerName, taxpayerTIN, declarationDate);
        this.taxableSales = sales;
        this.taxablePurchases = purchases;
        validateDeclaration();
    }

    @Override
    public double calculateTax() {
        taxAmount = (taxableSales - taxablePurchases) * 0.18;
        return taxAmount;
    }

    @Override
    public void validateDeclaration() {
        if (taxableSales < taxablePurchases) {
            throw new IllegalArgumentException("Sales must be greater than purchases for VAT.");
        }
        if (declarationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Declaration date cannot be in the future.");
        }
    }

    @Override
    public void generateReceipt() {
        System.out.println("\n--- VAT Tax Receipt ---");
        System.out.println("Taxpayer: " + taxpayerName + " (" + taxpayerTIN + ")");
        System.out.println("VAT Due: $" + taxAmount);
        System.out.println("Payment Status: " + (isPaid ? "PAID" : "NOT PAID"));
    }

    @Override
    public void enforceCompliance() {
        if (!isPaid && declarationDate.plusMonths(1).isBefore(LocalDate.now())) {
            System.out.println("!Penalty Applied: Late VAT Payment.");
            taxAmount *= 1.15;
        }
    }
}
