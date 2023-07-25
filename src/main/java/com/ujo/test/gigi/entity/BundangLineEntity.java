package com.ujo.test.gigi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BundangLineEntity extends BaseDateEntity{

    private String stationCode;
    private int distance;
    private String stationName;
    private int isDirectStop;
    private String relativeStation;
}
