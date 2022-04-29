package com.test.parkingslot.service.impl;

import com.test.parkingslot.dto.CreateBookingRequest;
import com.test.parkingslot.dto.SpotsNumberDto;
import com.test.parkingslot.exception.NoAvailableSpots;
import com.test.parkingslot.exception.NotFoundException;
import com.test.parkingslot.model.Spot;
import com.test.parkingslot.model.SpotStatus;
import com.test.parkingslot.repository.SpotRepository;
import com.test.parkingslot.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SpotServiceImpl implements SpotService {
    private final Set<String> bookedSpots = new HashSet<>();
    @Autowired
    SpotRepository spotRepository;

    @Override
    public SpotsNumberDto getSpots(SpotStatus status) {
        SpotsNumberDto spotsNumberDto = new SpotsNumberDto();
        switch (status) {
            case ALL:
                spotsNumberDto.setNumber(String.valueOf(spotRepository.findAll().size()));
                return spotsNumberDto;

            case FREE:
                spotsNumberDto.setNumber(String.valueOf(getAvailableNumberOfSpots()));
                return spotsNumberDto;

            case BUSY:
                spotsNumberDto.setNumber(String.valueOf(getNonAvailableNumberOfSpots()));
                return spotsNumberDto;

            default:
                throw new NoAvailableSpots();
        }
    }

    @Override
    public Spot changeStatus(Long id, boolean isFree, String carNumber) {
        Spot spot = spotRepository.findById(id).orElseThrow(NotFoundException::new);
        spot.setAvailableStatus(isFree);

        if (!isFree) {
            bookedSpots.remove(carNumber);
        }

        if (isFree) {
            bookedSpots.add(carNumber);
        }

        return spotRepository.save(spot);
    }

    @Override
    public void bookSpot(CreateBookingRequest request) {

        if (getAvailableNumberOfSpots() > 0 && !bookedSpots.contains(request.getCarNumber())) {
            bookedSpots.add(request.getCarNumber());
        } else throw new NoAvailableSpots();
    }

    @Override
    public void cancel(String carNumber) {
        bookedSpots.remove(carNumber);
    }


    private int getSpotAmount() {
        return spotRepository.findAllByAvailableStatusIsTrue().size();
    }

    private int getBusySpotAmount() {
        return spotRepository.findAllByAvailableStatusIsFalse().size();
    }

    private int getBookedSpotAmount() {
        return bookedSpots.size();
    }

    private int getAvailableNumberOfSpots() {
        return getSpotAmount() - getBookedSpotAmount();
    }

    private int getNonAvailableNumberOfSpots() {
        return getBusySpotAmount() + getBookedSpotAmount();
    }
}
