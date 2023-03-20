package com.qcells.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConvertibleCarTest {

    @Test
    public void shouldBuildConvertibleCar() {
        Engine engine = Engine.newBuilder()
                .withHorsePower(256)
                .withMilesPerGallon(35.0)
                .build();

        ConvertibleCar car = ConvertibleCar.newBuilder()
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
        assertEquals(RoofPosition.UP, car.getRoofPosition());
    }

    @Test
    public void testConvertibleCarFormatString() {
        Engine engine = Engine.newBuilder()
                .withHorsePower(256)
                .withMilesPerGallon(35.0)
                .build();

        Car car = ConvertibleCar.newBuilder()
                .withNumberOfSeats(5)
                .withName("Mercedes-Benz")
                .withProductionNumber("GLE250d")
                .withEngine(engine)
                .build();

        assertEquals("Mercedes-Benz GLE250d 5 seater (Convertible) with 256hp / 35.0MPG engine", car.toString());
    }

    @Test
    public void shouldThrowExceptionWhenNameIsNotProvided() {
        Throwable exception = assertThrows(NullPointerException.class, () -> ConvertibleCar.newBuilder()
                .withNumberOfSeats(5)
                .withProductionNumber("Cru909")
                .build());

        assertEquals("Name should be provided", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenInvalidNumberOfSeatsAreProvided() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> ConvertibleCar.newBuilder()
                .withNumberOfSeats(1)
                .withName("Mercedes")
                .withProductionNumber("GLE9011")
                .build());

        assertEquals("Number of seats should be at least 2", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenProductionNumberIsNotProvided() {
        Throwable exception = assertThrows(NullPointerException.class, () -> ConvertibleCar.newBuilder()
                .withNumberOfSeats(5)
                .withName("Holden")
                .build());

        assertEquals("Production number should be provided", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenEngineIsNotProvided() {
        Throwable exception = assertThrows(NullPointerException.class, () -> ConvertibleCar.newBuilder()
                .withNumberOfSeats(5)
                .withName("Holden")
                .withProductionNumber("CruiseLX12")
                .build());

        assertEquals("Engine should be provided", exception.getMessage());
    }

}