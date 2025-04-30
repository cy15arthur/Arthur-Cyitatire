package advancedtaxsystem;


import java.util.ArrayList;
import java.util.List;

public class TaxOfficer {
    private String officerId;
    private String fullName;
    private String assignedRegion;
    private List<TaxDeclaration> auditsConducted;

    public TaxOfficer(String officerId, String fullName, String assignedRegion) {
        this.officerId = officerId;
        this.fullName = fullName;
        this.assignedRegion = assignedRegion;
        this.auditsConducted = new ArrayList<>();
    }

    public void auditDeclaration(TaxDeclaration declaration) {
        auditsConducted.add(declaration);
        declaration.enforceCompliance();
    }

    public void generateAuditSummary() {
        System.out.println("\n--- Audit Summary by Officer " + fullName + " ---");
        for (TaxDeclaration td : auditsConducted) {
            System.out.println("Declaration ID: " + td.getDeclarationId() + ", Taxpayer: " + td.getTaxpayerName() + ", Paid: " + (td.isPaid() ? "Yes" : "No"));
        }
    }
}
