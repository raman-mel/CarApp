package com.qcells.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ConvertibleCar extends Car {
    private RoofPosition roofPosition;

    protected ConvertibleCar(final String name, final int numberOfSeats, final String productionNumber, final Engine engine) {
       super(name, numberOfSeats, productionNumber, engine);
       this.roofPosition = RoofPosition.UP;
    }

    public void roofUp() {
        if (roofPosition == RoofPosition.DOWN) {
            final String message = String.format("\nCar %s changing its roof position to %s", this.getName(),  RoofPosition.UP);
            System.out.println(message);
        }
        this.roofPosition = RoofPosition.UP;
    }

    public void roofDown() {
        if (roofPosition == RoofPosition.UP) {
            final String message = String.format("\nCar %s changing its roof position to %s", this.getName(),  RoofPosition.DOWN);
            System.out.println(message);
        }
        this.roofPosition = RoofPosition.UP;
    }

    public RoofPosition getRoofPosition() {
        return roofPosition;
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.0");
        return String.format("%s %s %d seater (Convertible) with %dhp / %sMPG engine", getName(), getProductionNumber(), getNumberOfSeats(),
                getEngine().getHorsePower(), formatter.format(getEngine().getMilesPerGallon()));
    }

    public static ConvertibleCarBuilder newBuilder() {
        return new ConvertibleCarBuilder();
    }

    public static class ConvertibleCarBuilder extends CarBuilder<ConvertibleCarBuilder> {
        @Override
        public ConvertibleCar build() {
            super.validateParams();
            return new ConvertibleCar(name, numberOfSeats, productionNumber, engine);
        }
    }

}
