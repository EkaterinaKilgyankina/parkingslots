package com.test.parkingslot.controller;

import com.test.parkingslot.dto.CreateBookingRequest;
import com.test.parkingslot.service.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final SpotService spotService;

    @PostMapping
    public void create(@RequestBody CreateBookingRequest request) {
        spotService.bookSpot(request);
    }

    @DeleteMapping
    public void cancel(@RequestParam String carNumber) {
        spotService.cancel(carNumber);
    }
}


