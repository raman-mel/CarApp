package com.qcells.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EngineTest {

    @Test
    public void shouldBuildEngine() {
        Engine engine = Engine.newBuilder()
                .withHorsePower(256)
                .withMilesPerGallon(35.0)
                .build();

        assertEquals(256, engine.getHorsePower());
        assertEquals(35.0, engine.getMilesPerGallon());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenInvalidHpProvided() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->  Engine.newBuilder()
                .withHorsePower(0)
                .build());
        assertEquals("Horsepower should be a positive value", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenInvalidMpgProvided() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->  Engine.newBuilder()
                .withHorsePower(20)
                .build());
        assertEquals("MilesPerGallon should be a positive value", exception.getMessage());
    }
}