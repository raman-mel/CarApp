package com.qcells.model;

import com.google.common.base.Preconditions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

public class Car {
    private final String name;
    private final int numberOfSeats;
    private final String productionNumber;
    private final Engine engine;

    private double currentSpeed;
    private String licensePlate;

    protected Car(final String name, final int numberOfSeats, final String productionNumber, final Engine engine) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.productionNumber = productionNumber;
        this.engine = engine;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getProductionNumber() {
        return productionNumber;
    }

    public Engine getEngine() {
        return engine;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getCurrentSpeedRounded() {
        return round(currentSpeed, 1);
    }

    /**
     * Accelerate the car based on acceleration percentage provided as input.
     * Input can be in the range of -1.0 to 1.0 i.e 20% acceleration will be passed as inout of 0.2
     * Negative inout indicates de-accelerating (slowing down the car)
     *
     * This implementation is based on a very basic formula that will increase or decrease the speed of the car
     * based on the acceleration percentage taking into account the engine's horsepower
     * i.e. Speed = Speed + (accelerationPercentge * HP)
     *
     * @param accPercentage
     */
    public void accelerate(double accPercentage) {
        Preconditions.checkArgument(accPercentage >= -1.0 && accPercentage <= 1.0,
                "Invalid acceleration value passed. Valid range is -1.0 to 1.0");
        this.currentSpeed = currentSpeed + (accPercentage * engine.getHorsePower());
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public static CarBuilder newBuilder() {
        return new CarBuilder();
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.0");
        return String.format("%s %s %d seater with %dhp / %sMPG engine", name, productionNumber, numberOfSeats,
                engine.getHorsePower(), formatter.format(engine.getMilesPerGallon()));
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (that == null || getClass() != that.getClass()) {
            return false;
        }

        return Objects.equals(name, ((Car) that).name) &&
                Objects.equals(licensePlate, ((Car) that).licensePlate) &&
                Objects.equals(productionNumber, ((Car) that).productionNumber) &&
                Objects.equals(engine,  ((Car) that).engine);
    }

    public static class CarBuilder<T extends CarBuilder> {
        protected String name;
        protected int numberOfSeats;
        protected String productionNumber;
        protected Engine engine;

        public T withName(final String name) {
            this.name = name;
            return (T) this;
        }

        public T withNumberOfSeats(final int numberOfSeats) {
            this.numberOfSeats = numberOfSeats;
            return (T) this;
        }

        public T withProductionNumber(final String productionNumber) {
            this.productionNumber = productionNumber;
            return (T) this;
        }

        public T withEngine(final Engine engine) {
            this.engine = engine;
            return (T) this;
        }

        public Car build() {
            validateParams();
            return new Car(name, numberOfSeats, productionNumber, engine);
        }

        protected void validateParams() {
            Preconditions.checkNotNull(name, "Name should be provided");
            Preconditions.checkArgument(numberOfSeats > 1, "Number of seats should be at least 2");
            Preconditions.checkNotNull(productionNumber, "Production number should be provided");
            Preconditions.checkNotNull(engine, "Engine should be provided");
        }
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
