package vehiclemanagementsystem;

import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Vehicle> vehicles = new ArrayList<>();
    private static final Set<String> registrationNumbers = new HashSet<>();
    private static final Set<String> vehicleIds = new HashSet<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n----- Vehicle Tax Management System -----");
            System.out.println("1. Register a new vehicle");
            System.out.println("2. View registered vehicles");
            System.out.println("3. Calculate tax for all vehicles");
            System.out.println("4. Generate tax reports");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    registerVehicle();
                    break;
                case "2":
                    viewRegisteredVehicles();
                    break;
                case "3":
                    calculateTaxes();
                    break;
                case "4":
                    generateTaxReports();
                    break;
                case "5":
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void registerVehicle() {
        System.out.println("\n--- Register a New Vehicle ---");

        String type = getValidatedVehicleType();
        String vehicleId = getValidatedVehicleId();
        String ownerName = getValidatedOwnerName();
        int yearOfFabrication = getValidatedYearOfFabrication();
        String registrationNumber = getValidatedRegistrationNumber();
        double baseTaxRate = getValidatedBaseTaxRate();

        Vehicle vehicle = null;

        switch (type.toLowerCase()) {
            case "car":
                boolean isElectric = getValidatedBoolean("Is the car electric? (true/false): ");
                vehicle = new Car(vehicleId, ownerName, yearOfFabrication, registrationNumber, baseTaxRate, isElectric);
                break;
            case "truck":
                double loadCapacity = getValidatedPositiveDouble("Enter load capacity in tons: ");
                vehicle = new Truck(vehicleId, ownerName, yearOfFabrication, registrationNumber, baseTaxRate, loadCapacity);
                break;
            case "motorcycle":
                int engineCapacity = getValidatedPositiveInt("Enter engine capacity in cc: ");
                vehicle = new Motorcycle(vehicleId, ownerName, yearOfFabrication, registrationNumber, baseTaxRate, engineCapacity);
                break;
            case "bus":
                int passengerCapacity = getValidatedPositiveInt("Enter passenger capacity: ");
                vehicle = new Bus(vehicleId, ownerName, yearOfFabrication, registrationNumber, baseTaxRate, passengerCapacity);
                break;
            case "suv":
                boolean fourWheelDrive = getValidatedBoolean("Is it a four-wheel drive (4WD)? (true/false): ");
                vehicle = new SUV(vehicleId, ownerName, yearOfFabrication, registrationNumber, baseTaxRate, fourWheelDrive);
                break;
        }

        if (vehicle != null) {
            vehicles.add(vehicle);
            registrationNumbers.add(registrationNumber);
            vehicleIds.add(vehicleId);
            System.out.println("Vehicle registered successfully!");
            System.out.println(vehicle);
        }
    }

    private static void viewRegisteredVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles registered yet.");
            return;
        }
        System.out.println("\n--- Registered Vehicles ---");
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private static void calculateTaxes() {
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles registered yet.");
            return;
        }
        System.out.println("\n--- Calculating Taxes ---");
        for (Vehicle v : vehicles) {
            System.out.println("Vehicle ID: " + v.getVehicleId() + ", Tax Amount: " + v.calculateTax());
        }
    }

    private static void generateTaxReports() {
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles registered yet.");
            return;
        }
        System.out.println("\n--- Tax Reports ---");
        for (Vehicle v : vehicles) {
            v.generateTaxReport();
        }
    }

    private static String getValidatedVehicleType() {
        System.out.print("Enter vehicle type (Car, Truck, Motorcycle, Bus, SUV): ");
        String type = scanner.nextLine().trim();
        while (!(type.equalsIgnoreCase("Car") || type.equalsIgnoreCase("Truck") ||
                type.equalsIgnoreCase("Motorcycle") || type.equalsIgnoreCase("Bus") ||
                type.equalsIgnoreCase("SUV"))) {
            System.out.print("Invalid type. Enter again (Car, Truck, Motorcycle, Bus, SUV): ");
            type = scanner.nextLine().trim();
        }
        return type;
    }

    private static String getValidatedVehicleId() {
        System.out.print("Enter vehicle ID (letters/numbers only): ");
        String id = scanner.nextLine().trim();
        while (!id.matches("[a-zA-Z0-9]+") || vehicleIds.contains(id)) {
            System.out.print("Invalid or duplicate ID. Enter a different one: ");
            id = scanner.nextLine().trim();
        }
        return id;
    }

    private static String getValidatedOwnerName() {
        System.out.print("Enter owner's name (letters only): ");
        String name = scanner.nextLine().trim();
        while (!name.matches("[a-zA-Z ]+")) {
            System.out.print("Invalid name. Enter again: ");
            name = scanner.nextLine().trim();
        }
        return name;
    }

    private static int getValidatedYearOfFabrication() {
        int year = -1;
        while (true) {
            try {
                System.out.print("Enter year of fabrication: ");
                year = Integer.parseInt(scanner.nextLine().trim());
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                if (year <= 1885 || year > currentYear) {
                    System.out.println("Year must be between 1886 and " + currentYear + ".");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return year;
    }

    private static String getValidatedRegistrationNumber() {
        System.out.print("Enter registration number (letters/numbers only): ");
        String reg = scanner.nextLine().trim();
        while (!reg.matches("[a-zA-Z0-9]+") || registrationNumbers.contains(reg)) {
            System.out.print("Invalid or duplicate registration number. Enter again: ");
            reg = scanner.nextLine().trim();
        }
        return reg;
    }

    private static double getValidatedBaseTaxRate() {
        double rate = -1;
        while (true) {
            try {
                System.out.print("Enter base tax rate: ");
                rate = Double.parseDouble(scanner.nextLine().trim());
                if (rate <= 0) {
                    System.out.println("Base tax rate must be positive.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return rate;
    }

    private static boolean getValidatedBoolean(String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();
        while (!(input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false"))) {
            System.out.print("Invalid input. Enter 'true' or 'false': ");
            input = scanner.nextLine().trim();
        }
        return Boolean.parseBoolean(input);
    }

    private static double getValidatedPositiveDouble(String message) {
        double value = -1;
        while (true) {
            try {
                System.out.print(message);
                value = Double.parseDouble(scanner.nextLine().trim());
                if (value <= 0) {
                    System.out.println("Value must be positive.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return value;
    }

    private static int getValidatedPositiveInt(String message) {
        int value = -1;
        while (true) {
            try {
                System.out.print(message);
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value <= 0) {
                    System.out.println("Value must be positive.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return value;
    }
}
