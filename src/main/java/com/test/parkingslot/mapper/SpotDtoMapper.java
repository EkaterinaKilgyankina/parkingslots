package com.test.parkingslot.mapper;

import com.test.parkingslot.dto.SpotDto;
import com.test.parkingslot.model.Spot;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpotDtoMapper {

    SpotDto toDto(Spot spot);

    List<SpotDto> toListDto(List<Spot> spot);
}
