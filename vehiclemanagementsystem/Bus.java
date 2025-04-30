package vehiclemanagementsystem;

public class Bus extends Vehicle {
    private int passengerCapacity;
    public Bus(String vehicleId, String ownerName, int yearOfFabrication, String registrationNumber, double baseTaxRate, int passengerCapacity) {
        super(vehicleId, ownerName, yearOfFabrication, registrationNumber, baseTaxRate, "Bus");
        if (passengerCapacity <= 0) throw new IllegalArgumentException("Passenger capacity must be positive.");
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public double calculateTax() {
        double tax = baseTaxRate;
        tax *= (1 + ((double) passengerCapacity / 10) * 0.02);
        if (java.time.Year.now().getValue() - yearOfFabrication > 20) {
            tax *= 1.10;
        }
        return tax;
    }

    @Override
    public void generateTaxReport() {
        System.out.println(this.toString() + ", Tax Due: " + calculateTax());
    }
}
