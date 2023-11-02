package com.ujo.gigi.speculation.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArrivalRealTimePositionRequestDTO {
    private String startStationCode;
    private String endStationCode;
}
