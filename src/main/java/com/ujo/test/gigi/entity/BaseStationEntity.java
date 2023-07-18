package com.ujo.test.gigi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BaseStationEntity {
    protected String stationCode;
    protected String stationName;
    protected String subwayLine;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
