package vehiclemanagementsystem;

public class Truck extends Vehicle {
    private double loadCapacity;
    public Truck(String vehicleId, String ownerName, int yearOfFabrication, String registrationNumber, double baseTaxRate, double loadCapacity) {
        super(vehicleId, ownerName, yearOfFabrication, registrationNumber, baseTaxRate, "Truck");
        if (loadCapacity <= 0) throw new IllegalArgumentException("Load capacity must be positive.");
        this.loadCapacity = loadCapacity;
    }

    @Override
    public double calculateTax() {
        double tax = baseTaxRate;
        if (java.time.Year.now().getValue() - yearOfFabrication > 15) {
            tax *= 1.15;
        }
        if (loadCapacity > 10) {
            tax *= 1.25;
        }
        return tax;
    }

    @Override
    public void generateTaxReport() {
        System.out.println(this.toString() + ", Tax Due: " + calculateTax());
    }
}
