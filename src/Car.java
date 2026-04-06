import java.util.Random;
import java.util.UUID;

public class Car {
    // Properties
    private String vin;
    private String make;
    private String model;
    private int year;
    private String color;
    private double fuelLevel;
    private double mileage;
    private boolean isEngineRunning;
    private int speed;
    private String transmission;
    private int numberOfDoors;
    private double fuelCapacity;
    private double fuelEfficiency;

    // Constructor
    public Car(String make, String model, int year, String color, String transmission, int numberOfDoors) {
        this.vin = generateVIN();
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.transmission = transmission;
        this.numberOfDoors = numberOfDoors;
        this.fuelLevel = 100.0;
        this.mileage = 0.0;
        this.isEngineRunning = false;
        this.speed = 0;
        this.fuelCapacity = 50.0;
        this.fuelEfficiency = 12.5; // km per liter
    }

    // Getters and Setters
    public String getVin() { return vin; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public double getFuelLevel() { return fuelLevel; }
    public double getMileage() { return mileage; }
    public int getSpeed() { return speed; }
    public boolean isEngineRunning() { return isEngineRunning; }

    // Generate random VIN
    private String generateVIN() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Start engine
    public boolean startEngine() {
        if (!isEngineRunning && fuelLevel > 0) {
            isEngineRunning = true;
            System.out.println("Engine started. Vroom vroom!");
            return true;
        } else if (fuelLevel <= 0) {
            System.out.println("Cannot start engine. No fuel!");
            return false;
        } else {
            System.out.println("Engine is already running!");
            return false;
        }
    }

    // Stop engine
    public void stopEngine() {
        if (isEngineRunning) {
            isEngineRunning = false;
            speed = 0;
            System.out.println("Engine stopped.");
        } else {
            System.out.println("Engine is already off!");
        }
    }

    // Accelerate
    public void accelerate(int increment) {
        if (!isEngineRunning) {
            System.out.println("Cannot accelerate. Start the engine first!");
            return;
        }

        if (fuelLevel <= 0) {
            System.out.println("Out of fuel! Cannot accelerate.");
            stopEngine();
            return;
        }

        int newSpeed = speed + increment;
        if (newSpeed <= 200) {
            speed = newSpeed;
            fuelLevel -= increment * 0.1;
            if (fuelLevel < 0) fuelLevel = 0;
            System.out.println("Accelerating. Current speed: " + speed + " km/h. Fuel: " + String.format("%.1f", fuelLevel) + "%");
        } else {
            System.out.println("Cannot exceed maximum speed of 200 km/h!");
        }
    }

    // Brake
    public void brake(int decrement) {
        if (!isEngineRunning) {
            System.out.println("Engine is off. Cannot brake.");
            return;
        }

        int newSpeed = speed - decrement;
        if (newSpeed >= 0) {
            speed = newSpeed;
            System.out.println("Braking. Current speed: " + speed + " km/h");
        } else {
            speed = 0;
            System.out.println("Car has stopped.");
        }
    }

    // Drive a certain distance
    public void drive(double kilometers) {
        if (!isEngineRunning) {
            System.out.println("Cannot drive. Start the engine first!");
            return;
        }

        if (speed <= 0) {
            System.out.println("Car is not moving. Accelerate first!");
            return;
        }

        double fuelNeeded = kilometers / fuelEfficiency;
        if (fuelNeeded <= fuelLevel) {
            fuelLevel -= fuelNeeded;
            mileage += kilometers;
            double time = kilometers / speed * 60;
            System.out.println("Drove " + kilometers + " km at " + speed + " km/h. Time: " + String.format("%.1f", time) + " minutes.");
            System.out.println("Fuel remaining: " + String.format("%.1f", fuelLevel) + " liters");
        } else {
            double maxDistance = fuelLevel * fuelEfficiency;
            System.out.println("Not enough fuel! Can only drive " + String.format("%.1f", maxDistance) + " km.");
        }
    }

    // Refuel
    public void refuel(double liters) {
        if (liters <= 0) {
            System.out.println("Invalid fuel amount!");
            return;
        }

        double newFuelLevel = fuelLevel + liters;
        if (newFuelLevel <= fuelCapacity) {
            fuelLevel = newFuelLevel;
            System.out.println("Added " + liters + " liters of fuel. Total: " + String.format("%.1f", fuelLevel) + " liters");
        } else {
            double added = fuelCapacity - fuelLevel;
            fuelLevel = fuelCapacity;
            System.out.println("Tank full! Added " + String.format("%.1f", added) + " liters");
        }
    }

    // Honk horn
    public void honk() {
        Random random = new Random();
        String[] honks = {"Beep!", "Honk!", "Beep beep!", "HONK HONK!", "TOOT TOOT!"};
        System.out.println(honks[random.nextInt(honks.length)]);
    }

    // Get car info
    public void getCarInfo() {
        System.out.println("========== CAR INFORMATION ==========");
        System.out.println("VIN: " + vin);
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Color: " + color);
        System.out.println("Transmission: " + transmission);
        System.out.println("Doors: " + numberOfDoors);
        System.out.println("Mileage: " + String.format("%.1f", mileage) + " km");
        System.out.println("Fuel Level: " + String.format("%.1f", fuelLevel) + " liters");
        System.out.println("Fuel Capacity: " + fuelCapacity + " liters");
        System.out.println("Fuel Efficiency: " + fuelEfficiency + " km/L");
        System.out.println("Engine: " + (isEngineRunning ? "Running" : "Off"));
        System.out.println("Speed: " + speed + " km/h");
        System.out.println("=====================================");
    }

    // Check fuel status
    public void checkFuel() {
        System.out.println("Fuel level: " + String.format("%.1f", fuelLevel) + "/" + fuelCapacity + " liters");
        if (fuelLevel < 10) {
            System.out.println("⚠️ LOW FUEL WARNING! ⚠️");
        }
    }

    // Paint the car
    public void paint(String newColor) {
        String oldColor = this.color;
        this.color = newColor;
        System.out.println("Car repainted from " + oldColor + " to " + newColor + "!");
    }
}