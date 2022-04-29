package com.test.parkingslot.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SpotsNumberDto {
    String number;
}
