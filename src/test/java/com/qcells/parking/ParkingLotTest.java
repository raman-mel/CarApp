package com.qcells.parking;

import com.qcells.exception.NoParkingSlotAvailableException;
import com.qcells.model.Car;
import com.qcells.model.Engine;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    @Test
    public void shouldBuildParkingLotWithCorrectCapacity() {
        ParkingLot parkingLot = new ParkingLot(7);
        assertEquals(7, parkingLot.getParkingSlots().size());
        assertEquals(7, parkingLot.getParkingSlots().stream().filter(slot -> slot.isEmpty()).count());
    }

    @Test
    public void shouldThrowWhenTryingToInstantiateParkingLotWithInvalidCapacity() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0));
        assertEquals("Minimum capacity of  1 is required to create a parking lot", exception.getMessage());
    }

    @Test
    public void shouldThrowWhenNullCarIsProvidedToPark() {
        ParkingLot parkingLot = new ParkingLot(5);
        Throwable exception = assertThrows(NullPointerException.class, () -> parkingLot.park(null));
        assertEquals("Car cannot be null", exception.getMessage());
    }

    @Test
    public void shouldThrowWhenCarWithoutLicensePlateIsProvidedToPark() {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car1 = buildRandomCar();
        car1.setLicensePlate(null);
        Throwable exception = assertThrows(NullPointerException.class, () -> parkingLot.park(car1));
        assertEquals("Cannot park the cat which does not have a license plate", exception.getMessage());
    }

    @Test
    public void shouldParkWhenSlotIsAvailable() {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car1 = buildRandomCar();
        Car car2 = buildRandomCar();
        Car car3 = buildRandomCar();
        Car car4 = buildRandomCar();
        Car car5 = buildRandomCar();
        Car car6 = buildRandomCar();

        parkingLot.park(car1);
        parkingLot.park(car2);
        parkingLot.park(car3);
        parkingLot.park(car4);
        parkingLot.park(car5);

        List<Slot> slots = parkingLot.getParkingSlots();

        assertEquals(1, slots.get(0).getSlotNumber());
        assertEquals(car1, slots.get(0).getParkedCar());

        assertEquals(2, slots.get(1).getSlotNumber());
        assertEquals(car2, slots.get(1).getParkedCar());

        assertEquals(3, slots.get(2).getSlotNumber());
        assertEquals(car3, slots.get(2).getParkedCar());

        assertEquals(4, slots.get(3).getSlotNumber());
        assertEquals(car4, slots.get(3).getParkedCar());

        assertEquals(5, slots.get(4).getSlotNumber());
        assertEquals(car5, slots.get(4).getParkedCar());

        // vacate a slot
        parkingLot.vacate(car3);

        // Now park another car
        parkingLot.park(car6);
        assertEquals(car6, parkingLot.getParkingSlots().get(2).getParkedCar());
    }

    @Test
    public void shouldThrowWhenParkWhenSlotIsNotAvailable() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car1 = buildRandomCar();
        Car car2 = buildRandomCar();
        Car car3 = buildRandomCar();
        Car car4 = buildRandomCar();

        parkingLot.park(car1);
        parkingLot.park(car2);
        parkingLot.park(car3);

        Throwable exception = assertThrows(NoParkingSlotAvailableException.class, () -> parkingLot.park(car4));
        assertEquals(String.format("No empty slot available to park the car %s - %s", car4.getName(), car4.getLicensePlate()), exception.getMessage());
    }


    private Car buildRandomCar() {
        String name = RandomStringUtils.random(7, true, false);
        String productionNumber = RandomStringUtils.random(6, true, true);
        Car car = Car.newBuilder()
                .withName(name)
                .withProductionNumber(productionNumber)
                .withNumberOfSeats(5)
                .withEngine(Engine.newBuilder()
                        .withHorsePower(100)
                        .withMilesPerGallon(30).build())
                .build();
        car.setLicensePlate(RandomStringUtils.random(6, true, true));
        return car;
    }

}