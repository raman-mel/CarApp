package com.qcells.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarTest {

    @Test
    public void shouldBuildCar() {
        Engine engine = Engine.newBuilder()
                .withHorsePower(256)
                .withMilesPerGallon(35.0)
                .build();

        Car car = Car.newBuilder()
                .withNumberOfSeats(5)
                .withName("Mercedes Benz")
                .withProductionNumber("MK16NZ")
                .withEngine(engine)
                .build();

        assertEquals("Mercedes Benz", car.getName());
        assertEquals("MK16NZ", car.getProductionNumber());
        assertEquals(5, car.getNumberOfSeats());
        assertEquals(256, car.getEngine().getHorsePower());
        assertEquals(35.0, car.getEngine().getMilesPerGallon());
        assertEquals(0.0, car.getCurrentSpeed());
    }

    @Test
    public void testCarFormatString() {
        Engine engine = Engine.newBuilder()
                .withHorsePower(256)
                .withMilesPerGallon(35.0)
                .build();

        Car car = Car.newBuilder()
                .withNumberOfSeats(5)
                .withName("Mercedes-Benz")
                .withProductionNumber("AGC456")
                .withEngine(engine)
                .build();

        assertEquals("Mercedes-Benz AGC456 5 seater with 256hp / 35.0MPG engine", car.toString());
    }

    @Test
    public void shouldThrowExceptionWhenNameIsNotProvided() {
        Throwable exception = assertThrows(NullPointerException.class, () -> Car.newBuilder()
                .withNumberOfSeats(5)
                .withProductionNumber("Cruise123")
                .build());

        assertEquals("Name should be provided", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenInvalidNumberOfSeatsAreProvided() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Car.newBuilder()
                .withNumberOfSeats(1)
                .withName("Mercedes GLE")
                .withProductionNumber("BHU890")
                .build());

        assertEquals("Number of seats should be at least 2", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenProductionNumberIsNotProvided() {
        Throwable exception = assertThrows(NullPointerException.class, () -> Car.newBuilder()
                .withNumberOfSeats(5)
                .withName("Holden")
                .build());

        assertEquals("Production number should be provided", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenEngineIsNotProvided() {
        Throwable exception = assertThrows(NullPointerException.class, () -> Car.newBuilder()
                .withNumberOfSeats(5)
                .withName("Holden")
                .withProductionNumber("Cru908")
                .build());

        assertEquals("Engine should be provided", exception.getMessage());
    }

    @Test
    public void shouldAccelerateCorrectly() {
        Engine engine = Engine.newBuilder()
                .withHorsePower(100)
                .withMilesPerGallon(35.0)
                .build();

        Car car = Car.newBuilder()
                .withNumberOfSeats(5)
                .withName("Mercedes Benz")
                .withProductionNumber("UIJK90")
                .withEngine(engine)
                .build();

        assertEquals(0.0, car.getCurrentSpeedRounded());

        car.accelerate(0.2);
        assertEquals(20.0, car.getCurrentSpeedRounded());

        car.accelerate(0.5);
        assertEquals(70.0, car.getCurrentSpeedRounded());

        car.accelerate(-0.2);
        assertEquals(50.0, car.getCurrentSpeedRounded());

        car.accelerate(-0.5);
        assertEquals(0.0, car.getCurrentSpeedRounded());
    }

    @Test
    public void accelerateShouldThrowWhenInvalidInoutProvided() {
        Engine engine = Engine.newBuilder()
                .withHorsePower(100)
                .withMilesPerGallon(35.0)
                .build();

        Car car = Car.newBuilder()
                .withNumberOfSeats(5)
                .withName("Mercedes Benz")
                .withProductionNumber("UIJK90")
                .withEngine(engine)
                .build();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> car.accelerate(-1.1));
        assertEquals("Invalid acceleration value passed. Valid range is -1.0 to 1.0", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> car.accelerate(1.1));
        assertEquals("Invalid acceleration value passed. Valid range is -1.0 to 1.0", exception.getMessage());
    }
}