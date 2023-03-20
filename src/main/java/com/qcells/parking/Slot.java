package com.qcells.parking;

import com.qcells.model.Car;

public class Slot {
    private final int slotNumber;
    private Car parkedCar;

    public Slot(final int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isEmpty() {
        return parkedCar == null;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void occupySlot(final Car car) {
        this.parkedCar = car;
    }

    public void vacateSlot() {
        this.parkedCar = null;
    }
}
