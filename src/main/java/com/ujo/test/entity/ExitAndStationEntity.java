package com.ujo.test.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExitAndStationEntity extends BaseStationEntity{
    private String stationCode;
    private String date;
    private int userCount17;
    private int userCount18;
    private int userCount19;
}
