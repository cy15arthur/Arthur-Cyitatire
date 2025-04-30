package vehiclemanagementsystem;

public class Motorcycle extends Vehicle {
    private int engineCapacity;

    public Motorcycle(String vehicleId, String ownerName, int yearOfFabrication, String registrationNumber, double baseTaxRate, int engineCapacity) {
        super(vehicleId, ownerName, yearOfFabrication, registrationNumber, baseTaxRate, "Motorcycle");
        if (engineCapacity <= 0) throw new IllegalArgumentException("Engine capacity must be positive.");
        this.engineCapacity = engineCapacity;
    }

    @Override
    public double calculateTax() {
        double tax = baseTaxRate;
        if (engineCapacity > 500) {
            tax *= 1.2; // 20% extra
        }
        int age = java.time.Year.now().getValue() - yearOfFabrication;
        tax *= (1 - 0.05 * (age / 5));
        return tax;
    }
    @Override
    public void generateTaxReport() {
        System.out.println(this.toString() + ", Tax Due: " + calculateTax());
    }
}
