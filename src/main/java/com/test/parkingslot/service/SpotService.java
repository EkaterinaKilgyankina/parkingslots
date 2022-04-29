package com.test.parkingslot.service;

import com.test.parkingslot.dto.CreateBookingRequest;
import com.test.parkingslot.dto.SpotsNumberDto;
import com.test.parkingslot.model.Spot;
import com.test.parkingslot.model.SpotStatus;

public interface SpotService {
    SpotsNumberDto getSpots(SpotStatus status);

    Spot changeStatus(Long id, boolean isFree,String carNumber);

    void bookSpot(CreateBookingRequest request);

    void cancel(String carNumber);
}
