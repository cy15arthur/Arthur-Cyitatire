package advancedtaxsystem;
import java.time.LocalDate;

public abstract class TaxDeclaration implements TaxCalculable, Receiptable {
    protected String declarationId;
    protected String taxpayerName;
    protected String taxpayerTIN;
    protected LocalDate declarationDate;
    protected double taxAmount;
    protected boolean isPaid;

    public TaxDeclaration(String declarationId, String taxpayerName, String taxpayerTIN, LocalDate declarationDate) {
        this.declarationId = declarationId;
        this.taxpayerName = taxpayerName;
        this.taxpayerTIN = taxpayerTIN;
        this.declarationDate = declarationDate;
        this.isPaid = false;
    }

    public abstract void validateDeclaration();
    public abstract void enforceCompliance();

    public String getDeclarationId() {
        return declarationId;
    }

    public String getTaxpayerName() {
        return taxpayerName;
    }

    public String getTaxpayerTIN() {
        return taxpayerTIN;
    }

    public LocalDate getDeclarationDate() {
        return declarationDate;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
