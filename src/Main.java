public class Main {
    public static void main (String[] args){
        System.out.println("Hello Maina are you mastering git ?");
        Car myCar = new Car("Toyota", "Camry", 2022, "Red", "Automatic", 4);

        // Display car info
        myCar.getCarInfo();

        // Start and drive
        myCar.startEngine();
        myCar.accelerate(30);
        myCar.drive(10);
        myCar.accelerate(20);
        myCar.drive(25);

        // Check fuel
        myCar.checkFuel();

        // Honk horn
        myCar.honk();

        // Refuel
        myCar.refuel(20);

        // Paint the car
        myCar.paint("Blue");

        // Stop engine
        myCar.stopEngine();

        // Final info
        myCar.getCarInfo();
    }
}
