package com.test.parkingslot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoAvailableSpots extends RuntimeException {
    private static final String MESSAGE = "unfortunately, no available spots";

    public NoAvailableSpots() {
        super(MESSAGE);
    }
}
