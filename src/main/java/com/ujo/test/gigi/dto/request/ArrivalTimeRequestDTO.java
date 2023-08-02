package com.ujo.test.gigi.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArrivalTimeRequestDTO {
    private String stationCode;
    private String arrivalTime;
    private String message;
}
