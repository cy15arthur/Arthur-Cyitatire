package vehiclemanagementsystem;

public class Car extends Vehicle {
    private boolean isElectric;
    public Car(String vehicleId, String ownerName, int yearOfFabrication, String registrationNumber, double baseTaxRate, boolean isElectric) {
        super(vehicleId, ownerName, yearOfFabrication, registrationNumber, baseTaxRate, "Car");
        this.isElectric = isElectric;
    }
    @Override
    public double calculateTax() {
        double tax = baseTaxRate;
        if (isElectric) {
            tax *= 0.8;
        }
        if (java.time.Year.now().getValue() - yearOfFabrication > 10) {
            tax *= 0.9;
        }
        return tax;
    }
    @Override
    public void generateTaxReport() {
        System.out.println(this.toString() + ", Tax Due: " + calculateTax());
    }
}
