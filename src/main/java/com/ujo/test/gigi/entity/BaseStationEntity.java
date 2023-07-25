package com.ujo.test.gigi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseStationEntity extends BaseDateEntity{
    protected String stationCode;
    protected String stationName;
    protected String subwayLine;
    protected int priority;
}
