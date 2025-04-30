package vehiclemanagementsystem;

public abstract class Vehicle {
    protected String vehicleId;
    protected String ownerName;
    protected int yearOfFabrication;
    protected String registrationNumber;
    protected double baseTaxRate;
    protected String vehicleType;

    public Vehicle(String vehicleId, String ownerName, int yearOfFabrication, String registrationNumber, double baseTaxRate, String vehicleType) {
        if (yearOfFabrication > java.time.Year.now().getValue()) {
            throw new   IllegalArgumentException("Year of fabrication cannot be in the future.");
        }
        this.vehicleId = vehicleId;
        this.ownerName = ownerName;
        this.yearOfFabrication = yearOfFabrication;
        this.registrationNumber = registrationNumber;
        this.baseTaxRate = baseTaxRate;
        this.vehicleType = vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }
    public String getVehicleType() {
        return vehicleType;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getYearOfFabrication() {
        return yearOfFabrication;
    }

    public void setYearOfFabrication(int year) {
        if (year > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Year cannot be in the future.");
        }
        this.yearOfFabrication = year;
    }

    public abstract double calculateTax();
    public abstract void generateTaxReport();

    @Override
    public String toString() {
        return "Vehicle Management System.Vehicle ID: " + vehicleId + ", Owner: " + ownerName + ", Year: " + yearOfFabrication +
                ", Reg No: " + registrationNumber + ", Base Tax: " + baseTaxRate + ", Type: " + vehicleType;
    }
}
