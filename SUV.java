package vehiclemanagementsystem;

public class SUV extends Vehicle {
    private boolean fourWheelDrive;

    public SUV(String vehicleId, String ownerName, int yearOfFabrication, String registrationNumber, double baseTaxRate, boolean fourWheelDrive) {
        super(vehicleId, ownerName, yearOfFabrication, registrationNumber, baseTaxRate, "SUV");
        this.fourWheelDrive = fourWheelDrive;
    }

    @Override
    public double calculateTax() {
        double tax = baseTaxRate;
        if (fourWheelDrive) {
            tax *= 1.10;
        }
        if (java.time.Year.now().getValue() - yearOfFabrication > 10) {
            tax *= 0.95;
        }
        return tax;
    }

    @Override
    public void generateTaxReport() {
        System.out.println(this.toString() + ", Tax Due: " + calculateTax());
    }
}
