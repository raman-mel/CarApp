package com.qcells.parking;

import com.google.common.base.Preconditions;
import com.qcells.exception.NoParkingSlotAvailableException;
import com.qcells.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLot {

    private final List<Slot> parkingSlots;

    public ParkingLot(final int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Minimum capacity of  1 is required to create a parking lot");
        }
        parkingSlots = new ArrayList<>(capacity);
        for(int i=1; i<= capacity; i++) {
            parkingSlots.add(new Slot(i));
        }
    }

    private Optional<Slot> findNextAvailableSlot() {
        return parkingSlots.stream()
                .filter(slot -> slot.isEmpty())
                .findFirst();
    }

    public List<Slot> getParkingSlots() {
        return parkingSlots;
    }

    public void park(Car car) throws NoParkingSlotAvailableException {
        Preconditions.checkNotNull(car, "Car cannot be null");
        Preconditions.checkNotNull(car.getLicensePlate(), "Cannot park the cat which does not have a license plate");
        Optional<Slot> availableSlot = findNextAvailableSlot();
        availableSlot.ifPresentOrElse(slot -> {
            slot.occupySlot(car);
            System.out.println(String.format("Slot no : %d has been occupied by car %s - %s", slot.getSlotNumber(), car.getName(), car.getLicensePlate()));
        }, () -> {
            throw new NoParkingSlotAvailableException(String.format("No empty slot available to park the car %s - %s", car.getName(), car.getLicensePlate()));
        });
    }

    public void vacate(Car car) {
        parkingSlots.stream()
                .filter(slot -> slot.getParkedCar().equals(car))
                .findFirst()
                        .ifPresent(slot -> {
                            slot.vacateSlot();
                            System.out.println(String.format("Slot no : %d has been vacated by car %s - %s", slot.getSlotNumber(), car.getName(), car.getLicensePlate()));
                        });

    }

    public void printSlotDirectory() {
        System.out.println("\nPrinting parking lot directory");
        parkingSlots.stream()
                .forEach(slot -> {
                    if (slot.isEmpty()) {
                        System.out.println(String.format("Slot no: %s is empty", slot.getSlotNumber()));
                    } else {
                        System.out.println(String.format("Slot no. %s is occupied by %s-%s", slot.getSlotNumber(),
                                slot.getParkedCar().getName(), slot.getParkedCar().getLicensePlate()));
                    }
                });
    }

}
