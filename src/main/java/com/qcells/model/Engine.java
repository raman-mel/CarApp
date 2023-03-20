package com.qcells.model;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class Engine {
    private final int horsePower;
    private final double milesPerGallon;

    private Engine(final int horsePower, final double milesPerGallon) {
        this.horsePower = horsePower;
        this.milesPerGallon = milesPerGallon;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public double getMilesPerGallon() {
        return milesPerGallon;
    }

    public static EngineBuilder newBuilder() {
        return new EngineBuilder();
    }

    public static class EngineBuilder {
        private int horsePower;
        private double milesPerGallon;

        public EngineBuilder withHorsePower(final int horsePower) {
            this.horsePower = horsePower;
            return this;
        }

        public EngineBuilder withMilesPerGallon(final double milesPerGallon) {
            this.milesPerGallon = milesPerGallon;
            return this;
        }

        public Engine build() {
            Preconditions.checkArgument(horsePower > 0, "Horsepower should be a positive value");
            Preconditions.checkArgument(milesPerGallon > 0.0, "MilesPerGallon should be a positive value");

            return new Engine(horsePower, milesPerGallon);
        }

    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (that == null || getClass() != that.getClass()) {
            return false;
        }

        return Objects.equals(horsePower, ((Engine) that).horsePower) &&
                Objects.equals(milesPerGallon, ((Engine) that).milesPerGallon);
    }

}
