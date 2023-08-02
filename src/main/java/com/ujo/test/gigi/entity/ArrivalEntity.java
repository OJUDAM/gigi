package com.ujo.test.gigi.entity;

import com.ujo.test.common.utils.DateUtils;
import com.ujo.test.gigi.dto.request.ArrivalTimeRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ArrivalEntity extends BaseDateEntity{
    private String stationCode;
    private String arrivalYear;
    private String arrivalMonth;
    private String arrivalDay;
    private String arrivalTime;
    private String delayTime;
    private String message;
}
