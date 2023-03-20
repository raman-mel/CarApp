package com.qcells;

import com.qcells.model.Car;
import com.qcells.model.ConvertibleCar;
import com.qcells.model.Engine;
import com.qcells.parking.ParkingLot;

import java.util.Arrays;

public class App {
    private static double ACCELERATION_PERCENTAGE = 0.2;
    private static double RACE_SPEED_TARGET = 200;

    private Car prius;
    private ConvertibleCar porche;
    private Car holden;
    private Car anotherPrius;

    public static void main(String... args) {
        new App().start();
    }

    private void start() {
        setupCars();
        startRace();
        testParkingLot();
    }

    private void startRace() {
        // Lower the roof of the Boxster
        porche.roofDown();
        boolean raceEnds = false;
        System.out.println("\n=================RACE BEGINS=============================================\n");
        while (raceEnds != true) {
            accelerate(prius, porche);
            printSpeed(prius, porche);

            if (prius.getCurrentSpeedRounded() >= RACE_SPEED_TARGET || porche.getCurrentSpeedRounded() >= RACE_SPEED_TARGET) {
                raceEnds = true;
                String winner = prius.getCurrentSpeedRounded() > porche.getCurrentSpeedRounded() ? prius.getName() : porche.getName();
                System.out.println(String.format("\n%s has reached the speed of %s first and is declared as WINNER", winner, RACE_SPEED_TARGET));
                System.out.println("\n=================RACE ENDS=============================================");
            }
        }
    }

    private void testParkingLot() {
        System.out.println("\n=================PARKING CHECK START=============================================");

        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.printSlotDirectory();
        System.out.println();
        parkingLot.park(porche);
        parkingLot.park(prius);
        parkingLot.park(holden);
        parkingLot.vacate(porche);
        parkingLot.park(anotherPrius);
        parkingLot.printSlotDirectory();

        System.out.println("\n=================PARKING CHECK END=============================================");
    }

    private void accelerate(Car... cars) {
        Arrays.asList(cars).forEach(car -> car.accelerate(ACCELERATION_PERCENTAGE));
    }

    private void printSpeed(Car... cars) {
        Arrays.asList(cars).forEach(car -> System.out.println(String.format("Current speed of %s : %s mph", car.getName(), car.getCurrentSpeedRounded())));
    }

    private Engine buildEngine(int horsePower, double milesPerGallon) {
        return Engine.newBuilder()
                .withHorsePower(horsePower)
                .withMilesPerGallon(milesPerGallon)
                .build();
    }

    private void setupCars() {
        prius = Car.newBuilder()
                .withName("Toyota Prius")
                .withProductionNumber("VIN345")
                .withNumberOfSeats(5)
                .withEngine(buildEngine(121, 53))
                .build();
        prius.setLicensePlate("ABC111");
        System.out.println("Created " + prius);

        porche = ConvertibleCar.newBuilder()
                .withName("Porche Boxster")
                .withProductionNumber("LE250")
                .withNumberOfSeats(2)
                .withEngine(buildEngine(265, 32))
                .build();
        porche.setLicensePlate("JATT16");
        System.out.println("Created " + porche);

        holden = Car.newBuilder()
                .withName("Holden Cruze")
                .withProductionNumber("ABC123")
                .withNumberOfSeats(5)
                .withEngine(buildEngine(90, 45))
                .build();
        holden.setLicensePlate("XYZ6HN");
        System.out.println("Created " + holden);

        anotherPrius = Car.newBuilder()
                .withName("Toyota Prius")
                .withProductionNumber("VIN121")
                .withNumberOfSeats(5)
                .withEngine(buildEngine(121, 53))
                .build();
        anotherPrius.setLicensePlate("16NCB");
        System.out.println("Created " + anotherPrius);
    }

}
