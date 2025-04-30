package advancedtaxsystem;

public class Taxpayer {
    private String tin;
    private String name;
    private String type; // Individual or Company
    private int complianceScore;

    public Taxpayer(String tin, String name, String type) {
        if (tin.length() != 9 || !tin.matches("\\d+")) {
            throw new IllegalArgumentException("TIN must be 9 digits.");
        }
        this.tin = tin;
        this.name = name;
        this.type = type;
        this.complianceScore = 100;
    }

    public String getTin() {
        return tin;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getComplianceScore() {
        return complianceScore;
    }

    public void decreaseCompliance(int amount) {
        complianceScore -= amount;
        if (complianceScore < 0) complianceScore = 0;
    }

    public void increaseCompliance(int amount) {
        complianceScore += amount;
        if (complianceScore > 100) complianceScore = 100;
    }
}
