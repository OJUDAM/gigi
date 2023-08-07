package com.ujo.test.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StationEntity extends BaseEntity{
    private String stationCode;
    private String stationName;
    private String subwayLine;
    private int isBatchable;
}