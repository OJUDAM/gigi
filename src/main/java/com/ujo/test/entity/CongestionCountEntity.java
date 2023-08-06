package com.ujo.test.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CongestionCountEntity {
    private String stationCode;
    private String stationName;
    private String subwayLine;
    private String day;
    private String hour;
    private int congestionMin00;
    private int congestionMin10;
    private int congestionMin20;
    private int congestionMin30;
    private int congestionMin40;
    private int congestionMin50;
    private int userCountDate;
    private int userCount;
    private int directAt;
}
