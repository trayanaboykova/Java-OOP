package Lesson05_Polymorphism.Exercise.P01_Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carInput = scanner.nextLine().split("\\s+");
        double fuelQuantity = Double.parseDouble(carInput[1]);
        double litersPerKm = Double.parseDouble(carInput[2]);

        VehicleImpl car = new Car(fuelQuantity, litersPerKm);

        String[] truckInput = scanner.nextLine().split("\\s+");
        fuelQuantity = Double.parseDouble(truckInput[1]);
        litersPerKm = Double.parseDouble(truckInput[2]);

        VehicleImpl truck = new Truck(fuelQuantity, litersPerKm);

        int numOfCommands = Integer.parseInt(scanner.nextLine());


        for (int i = 0; i < numOfCommands; i++) {

            String[] commandParts = scanner.nextLine().split("\\s+");
            String command = commandParts[0];
            String vehicleType = commandParts[1];

            if (vehicleType.equals("Car")) {
                if (command.equals("Drive")) {
                    double distance = Double.parseDouble(commandParts[2]);
                    car.drive(distance);
                } else if (command.equals("Refuel")) {
                    double fuel = Double.parseDouble(commandParts[2]);
                    car.refuel(fuel);
                }
            } else if (vehicleType.equals("Truck")) {
                if (command.equals("Drive")) {
                    double distance = Double.parseDouble(commandParts[2]);
                    truck.drive(distance);
                } else if (command.equals("Refuel")) {
                    double fuel = Double.parseDouble(commandParts[2]);
                    truck.refuel(fuel);
                }
            }
        }

        System.out.println(car);
        System.out.println(truck);
    }
}
