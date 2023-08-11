package com.ujo.gigi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StatAndStationEntity extends BaseStationEntity{

    private String stationCode;
    private String day;
    private String hour;
    private int congestionMin00;
    private int congestionMin10;
    private int congestionMin20;
    private int congestionMin30;
    private int congestionMin40;
    private int congestionMin50;
    private int upDnLine;
    private int directAt;
    private String prevStationCode;
    private String startStationCode;
    private String endStationCode;
    private String startDate;
    private String endDate;
}
