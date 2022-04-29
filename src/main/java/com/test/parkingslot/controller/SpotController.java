package com.test.parkingslot.controller;

import com.test.parkingslot.dto.SpotDto;
import com.test.parkingslot.dto.SpotsNumberDto;
import com.test.parkingslot.mapper.SpotDtoMapper;
import com.test.parkingslot.model.SpotStatus;
import com.test.parkingslot.service.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/spots")
public class SpotController {
    private final SpotService spotService;
    private final SpotDtoMapper mapper;

    @GetMapping
    public SpotsNumberDto getSpots(@RequestParam(value = "status", defaultValue = "ALL") SpotStatus status) {
        return spotService.getSpots(status);
    }

    @PutMapping("/{id}")
    public SpotDto changeStatus(@PathVariable Long id,
                                @RequestParam boolean isFree,
                                @RequestParam String carNumber) {
        return mapper.toDto(spotService.changeStatus(id, isFree, carNumber));
    }

}


