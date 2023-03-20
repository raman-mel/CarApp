package com.qcells.exception;

public class NoParkingSlotAvailableException extends RuntimeException {

    private String message;

    public NoParkingSlotAvailableException(String message) {
        super(message);
    }
}
